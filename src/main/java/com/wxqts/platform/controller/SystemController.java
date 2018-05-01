package com.wxqts.platform.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wxqts.platform.bean.SysDataGroup;
import com.wxqts.platform.bean.SysDataItem;
import com.wxqts.platform.bean.SysIpForbidden;
import com.wxqts.platform.bean.SysUser;
import com.wxqts.platform.dto.LoginInfo;
import com.wxqts.platform.dto.PageInfo;
import com.wxqts.platform.service.SysUserService;
import com.wxqts.platform.service.SystemService;
import com.wxqts.platform.utils.ResponseCode;
import com.wxqts.platform.utils.Result;
import com.wxqts.platform.utils.StringUtil;

/**
 * 系统功能模块
 */
@Controller
@RequestMapping("system")
public class SystemController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(SystemController.class);
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SystemService systemService;

    /**
     * 引导页
     *
     * @return
     */
    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public String index() {
        return "system/index";
    }

    /**
     * 登录
     *
     * @param loginName 登录名
     * @param password  密码
     * @param platform  终端类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login(@RequestParam String loginName,
                        @RequestParam String password,
                        @RequestParam int platform,
                        HttpServletRequest request) throws Exception {
        
        SysUser user = sysUserService.selectByLoginName(loginName);
        if (user == null) {
            return Result.instance(ResponseCode.unknown_account.getCode(), ResponseCode.unknown_account.getMsg());
        }
        if (user.getStatus() == 3) {
            return Result.instance(ResponseCode.forbidden_account.getCode(), ResponseCode.forbidden_account.getMsg());
        }
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(loginName, password));
        LoginInfo loginInfo = sysUserService.login(user, subject.getSession().getId(), platform);
        subject.getSession().setAttribute("loginInfo", loginInfo);
        log.debug("登录成功");
        return Result.success(loginInfo);
    }

    /**
     * 退出
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success();
    }

    

    /**
     * 用户状态
     *
     * @return
     */
    @RequiresPermissions("user:loginStatu:list")
    @RequestMapping(value = "online", method = RequestMethod.GET)
    public String online() {
        return "system/online";
    }

    /**
     * 在线用户列表
     *
     * @param page 起始页码
     * @param rows 分页大小
     * @return
     */
    @RequiresPermissions("user:loginStatu:list")
    @ResponseBody
    @RequestMapping(value = "online/list", method = RequestMethod.GET)
    public PageInfo onlineList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "30") int rows) {
        PageInfo pageInfo = systemService.selectLogStatus(page, rows);
        return pageInfo;
    }

    /**
     * 强制用户下线
     *
     * @param userIds 用户ids
     * @return
     */
    @RequiresPermissions("user:loginout")
    @ResponseBody
    @RequestMapping(value = "forceLogout", method = RequestMethod.GET)
    public Result forceLogout(@RequestParam String userIds) {
        System.out.println("userIds = [" + userIds + "]");
        String[] ids = userIds.split(",");
        for (String id : ids) {
            systemService.forceLogout(Long.parseLong(id));
        }
        return Result.success();
    }

    /**
     * 日志页面
     *
     * @return
     */
    @RequiresPermissions("log:list")
    @RequestMapping(value = "log")
    public String log() {
        return "system/log";
    }

    /**
     * 查询日志列表
     *
     * @param page   起始页码
     * @param rows   分页大小
     * @param sort   排序字段
     * @param order  排序规则
     * @param method 请求执行方法
     * @param url    请求url
     * @param param  请求参数
     * @param result 请求响应内容
     * @return
     */
    @RequiresPermissions("log:list")
    @ResponseBody
    @RequestMapping(value = "log/list", method = RequestMethod.GET)
    public PageInfo logList(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "30") int rows,
                            @RequestParam(defaultValue = "id") String sort,
                            @RequestParam(defaultValue = "desc") String order,
                            @RequestParam(defaultValue = "", required = false) String method,
                            @RequestParam(defaultValue = "", required = false) String url,
                            @RequestParam(defaultValue = "", required = false) String param,
                            @RequestParam(defaultValue = "", required = false) String result) {
        PageInfo pageInfo = systemService.selectLog(page, rows, StringUtil.camelToUnderline(sort), order, method, url, param, result);
        return pageInfo;
    }

    /**
     * 新增字典组
     *
     * @param name        名称
     * @param description 描述
     * @return
     */
    @RequiresPermissions("data:group:insert")
    @ResponseBody
    @RequestMapping(value = "dataGroup/insert", method = RequestMethod.POST)
    public Result dataGroupInsert(@RequestParam String name,
                                  @RequestParam String description) {
        boolean isExistName = systemService.isExistDataGroupName(name);
        if (isExistName) {
            return Result.error(ResponseCode.name_already_exist.getMsg());
        }
        SysDataGroup sysDataGroup = new SysDataGroup();
        sysDataGroup.setName(name);
        sysDataGroup.setDescription(description);
        sysDataGroup.setParentId(0L);
        sysDataGroup.setIsFinal(2);
        Long id = systemService.insertSysDataGroup(sysDataGroup);
        return Result.success(id);
    }

    /**
     * 字典组列表
     *
     * @return
     */
    @RequiresPermissions("data:group:list")
    @ResponseBody
    @RequestMapping(value = "dataGroup/list", method = RequestMethod.GET)
    public List<SysDataGroup> dataGroupList() {
        List<SysDataGroup> list = systemService.selectDataGroupList();
        return list;
    }

    @RequestMapping(value = "data", method = RequestMethod.GET)
    public String data() {
        return "system/data";
    }

    /**
     * 新增数据字典
     *
     * @param key         key
     * @param value       value
     * @param description 描述
     * @param groupId     字典组
     * @return
     */
    @RequiresPermissions("data:insert")
    @ResponseBody
    @RequestMapping(value = "data/insert", method = RequestMethod.POST)
    public Result dataInsert(@RequestParam String key,
                             @RequestParam String value,
                             @RequestParam String description,
                             @RequestParam long groupId) {
        boolean isExistName = systemService.isExistDataItemKeyName(key, groupId);
        if (isExistName) {
            return Result.error(ResponseCode.name_already_exist.getMsg());
        }
        SysDataItem sysDataItem = new SysDataItem();
        sysDataItem.setKeyName(key);
        sysDataItem.setKeyValue(value);
        sysDataItem.setSysDataGroupId(groupId);
        sysDataItem.setDescription(description);
        long id = systemService.insertSysDataItem(sysDataItem);
        return Result.success(id);
    }


    /**
     * 删除字典
     *
     * @param id
     * @return
     */
    @RequiresPermissions("data:delete")
    @ResponseBody
    @RequestMapping(value = "data/delete", method = RequestMethod.GET)
    public Result dataDelete(@RequestParam Long id) {
        systemService.deleteDataItemById(id);
        return Result.success();
    }

    /**
     * 更新字典
     *
     * @param id          id
     * @param key         key
     * @param value       value
     * @param description 描述
     * @param groupId     字典组
     * @return
     */
    @RequiresPermissions("data:update")
    @ResponseBody
    @RequestMapping(value = "data/update", method = RequestMethod.POST)
    public Result dataUpdate(@RequestParam Long id,
                             @RequestParam String key,
                             @RequestParam String value,
                             @RequestParam String description,
                             @RequestParam long groupId) {
        SysDataItem sysDataItem = systemService.selectDataItemById(id);
        if (sysDataItem == null) {
            return Result.error(ResponseCode.data_not_exist.getMsg());
        }
        if (sysDataItem.getIsFinal() == 2) {
            return Result.error(ResponseCode.can_not_edit.getMsg());
        }
        boolean isExistDataItemNameExcludeId = systemService.isExistDataItemNameExcludeId(id, key, groupId);
        if (isExistDataItemNameExcludeId) {
            return Result.error(ResponseCode.name_already_exist.getMsg());
        }
        sysDataItem.setKeyName(key);
        sysDataItem.setKeyValue(value);
        sysDataItem.setSysDataGroupId(groupId);
        sysDataItem.setDescription(description);
        systemService.updateDateItem(sysDataItem);

        return Result.success();
    }

    /**
     * 查询字典详情
     *
     * @return
     */
    @RequiresPermissions("data:select")
    @ResponseBody
    @RequestMapping(value = "data/select", method = RequestMethod.GET)
    public Result dataSelect() {
        return Result.success();
    }

    /**
     * 字典列表
     *
     * @param page 起始页码
     * @param rows 分页大小
     * @return
     */
    @RequiresPermissions("data:list")
    @ResponseBody
    @RequestMapping(value = "data/list", method = RequestMethod.GET)
    public PageInfo dataList(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "30") int rows) {
        PageInfo pageInfo = systemService.selectDataItemPage(page, rows);
        return pageInfo;
    }

    /**
     * ip页面跳转
     *
     * @return
     */
    @RequiresPermissions("ip:list")
    @RequestMapping(value = "ip", method = RequestMethod.GET)
    public String ip() {
        return "system/ip";
    }

    /**
     * 插入ip
     *
     * @param ip          ip地址
     * @param expireTime  过期时间
     * @param description 说明
     * @return
     */
    @RequiresPermissions("ip:insert")
    @ResponseBody
    @RequestMapping(value = "ip/insert", method = RequestMethod.POST)
    public Result ipInsert(@RequestParam String ip,
                           @RequestParam String expireTime,
                           @RequestParam String description) throws ParseException {
        boolean isExistIp = systemService.isExistIp(ip);
        if (isExistIp) {
            return Result.error(ResponseCode.name_already_exist.getMsg());
        }
        SysIpForbidden sysIpForbidden = new SysIpForbidden();
        sysIpForbidden.setIp(ip);
        sysIpForbidden.setExpireTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expireTime));
        sysIpForbidden.setDescription(description);
        Long id = systemService.insertIp(sysIpForbidden);
        return Result.success(id);
    }

    /**
     * 删除ip
     *
     * @param id
     * @return
     */
    @RequiresPermissions("ip:delete")
    @ResponseBody
    @RequestMapping(value = "ip/delete", method = RequestMethod.GET)
    public Result ipDelete(@RequestParam long id) {
        systemService.deleteIp(id);
        return Result.success();
    }

    /**
     * 更新ip
     *
     * @param id,         id,
     * @param ip          ip地址
     * @param expireTime  过期时间
     * @param description 说明
     * @return
     */
    @RequiresPermissions("ip:update")
    @ResponseBody
    @RequestMapping(value = "ip/update", method = RequestMethod.POST)
    public Result ipUpdate(@RequestParam long id,
                           @RequestParam String ip,
                           @RequestParam String expireTime,
                           @RequestParam String description) throws ParseException {
        boolean isExistIpExcludeId = systemService.isExistIpExcludeId(ip, id);
        if (isExistIpExcludeId) {
            return Result.error(ResponseCode.name_already_exist.getMsg());
        }
        SysIpForbidden sysIpForbidden = new SysIpForbidden();
        sysIpForbidden.setId(id);
        sysIpForbidden.setIp(ip);
        sysIpForbidden.setExpireTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expireTime));
        sysIpForbidden.setDescription(description);
        systemService.updateIp(sysIpForbidden);
        return Result.success();
    }

    /**
     * 查询ip列表
     *
     * @param page
     * @param rows
     * @return
     */
    @RequiresPermissions("ip:list")
    @ResponseBody
    @RequestMapping(value = "ip/list", method = RequestMethod.GET)
    public PageInfo ipSelect(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "30") int rows) {
        PageInfo pageInfo = systemService.selectIp(page, rows);
        return pageInfo;
    }

    @ResponseBody
    @RequestMapping(value = "ip/intercept", method = RequestMethod.GET)
    public Result intercept(@RequestParam boolean open) {
        //启用
        if (open == true) {
            systemService.openIpIntercept();
        }
        //禁用
        if (open == false) {
            systemService.closeIpIntercept();
        }
        return Result.success();
    }

    @ResponseBody
    @RequestMapping(value = "ip/intercept/status")
    public Result interceptStatus() {
        boolean ip_forbidden = systemService.selectIPForbiddenStatus();
        return Result.success(ip_forbidden);
    }

}
