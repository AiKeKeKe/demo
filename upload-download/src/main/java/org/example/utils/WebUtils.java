package org.example.utils;

import lombok.SneakyThrows;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Optional;

public class WebUtils {

    public static HttpServletRequest request() {
        return Optional.ofNullable((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).map(servletRequestAttributes -> servletRequestAttributes.getRequest()).orElse(null);
    }

    @SneakyThrows
    public static void downloadBinaryFile(HttpServletResponse response, InputStream inputStream, String fileName, Long fileSize) {
        downloadBinaryFileWithBom(response, inputStream, fileName, fileSize, false);
    }

    @SneakyThrows
    public static void downloadBinaryFileWithBom(HttpServletResponse response, InputStream inputStream, String fileName, Long fileSize, boolean withBom) {
        InputStreamReader reader = null;
        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            response.addHeader("Content-Length", String.valueOf(fileSize));
            response.setContentType("application/force-download");
            reader = new InputStreamReader(inputStream, "UTF-8");
            if (withBom) {
                response.getOutputStream().write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
            }
            IOUtils.copy(inputStream, response.getOutputStream());
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(reader);
        }
    }
}
