package com.changyue.j2eefinal.utils;

import com.changyue.j2eefinal.error.BusinessException;
import com.changyue.j2eefinal.error.BusinessStateEnum;
import com.changyue.j2eefinal.model.UserDO;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @program: j2ee-final
 * @description: 图片上传
 * @author: 袁阊越
 * @create: 2019-12-21 22:09
 */
public class ImageUpload {
    /**
     * 上传头像
     *
     * @param request 请求域
     * @param name    图片的名称
     * @throws BusinessException 异常
     */
    public static String uploadImg(HttpServletRequest request, String name) throws BusinessException {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (resolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartImg = multipartHttpServletRequest.getFile("img");
            if (multipartImg != null) {
                // 获取上传文件的原始名称
                String originalFilename = multipartImg.getOriginalFilename();
                // 设置上传文件的保存地址目录
                String dirPath =
                        request.getServletContext().getRealPath("/upload/");
                File filePath = new File(dirPath);
                // 如果保存文件的地址不存在，就先创建目录
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                // 使用UUID重新命名上传的文件名称(上传人_uuid_原始文件名称)
                String newFilename = name + "_" + UUID.randomUUID() +
                        "_" + originalFilename;
                System.out.println(dirPath + filePath);
                try {
                    multipartImg.transferTo(new File(dirPath + newFilename));
                    return "/upload/" + newFilename;
                } catch (IOException e) {
                    throw new BusinessException(BusinessStateEnum.USER_IMG_UPLOAD_FAIL);
                }
            }
        }
        return null;
    }

}
