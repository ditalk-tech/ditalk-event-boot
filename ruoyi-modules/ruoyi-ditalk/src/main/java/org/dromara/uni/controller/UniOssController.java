package org.dromara.uni.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.hutool.core.util.ObjectUtil;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.R;
import org.dromara.common.enums.UniRoleKeyEnum;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.common.web.core.BaseController;
import org.dromara.system.domain.vo.SysOssUploadVo;
import org.dromara.system.domain.vo.SysOssVo;
import org.dromara.system.service.ISysOssService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * OSS 对象存储 —— 只能操作属于自己的文件
 *
 * @author weidixian
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/uni/oss")
public class UniOssController extends BaseController {


    private final ISysOssService ossService;

    /**
     * 查询OSS对象基于id串
     *
     * @param ossIds OSS对象ID串
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @GetMapping("/listByIds/{ossIds}")
    public R<List<SysOssVo>> listByIds(@NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ossIds) {
        List<SysOssVo> list = ossService.listByIds(Arrays.asList(ossIds));
        // 过滤还是自己创建的文件
        list.removeIf(vo -> !vo.getCreateBy().equals(LoginHelper.getUserId()));
        return R.ok(list);
    }

    /**
     * 上传OSS对象存储
     *
     * @param file 文件
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "OSS对象存储", businessType = BusinessType.INSERT)
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<SysOssUploadVo> upload(@RequestPart("file") MultipartFile file) {
        if (ObjectUtil.isNull(file)) {
            return R.fail("上传文件不能为空");
        }
        SysOssVo oss = ossService.upload(file);
        SysOssUploadVo uploadVo = new SysOssUploadVo();
        uploadVo.setUrl(oss.getUrl());
        uploadVo.setFileName(oss.getOriginalName());
        uploadVo.setOssId(oss.getOssId().toString());
        return R.ok(uploadVo);
    }

//    /**
//     * 下载OSS对象
//     *
//     * @param ossId OSS对象ID
//     */
//    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
//    @GetMapping("/download/{ossId}")
//    public void download(@PathVariable Long ossId, HttpServletResponse response) throws IOException {
//        ossService.download(ossId, response);
//    }

    /**
     * 删除OSS对象存储
     *
     * @param ossIds OSS对象ID串
     */
    @SaCheckRole(value = {UniRoleKeyEnum.MP_WEIXIN_STR})
    @Log(title = "OSS对象存储", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove/{ossIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ossIds) {
        List<SysOssVo> list = ossService.listByIds(Arrays.asList(ossIds));
        // 判断文件是否属于自己，否则抛出异常提示权限不足
        for (SysOssVo vo : list) {
            if (!vo.getCreateBy().equals(LoginHelper.getUserId())) {
                return R.fail("权限不足");
            }
        }
        return toAjax(ossService.deleteWithValidByIds(List.of(ossIds), true));
    }
}
