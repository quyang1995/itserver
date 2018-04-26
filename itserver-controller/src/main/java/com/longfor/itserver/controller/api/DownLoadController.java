package com.longfor.itserver.controller.api;

import com.longfor.itserver.controller.base.BaseController;
import com.longfor.itserver.entity.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author wax
 *         Created on 2017/9/11 上午10：50
 * @version v1.0
 */
@RequestMapping(value = "/down/load")
@Controller
public class DownLoadController extends BaseController {

    @RequestMapping(value = "/bug/{i}")
    public void bugDown(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "i") Long id, Model model) throws IOException {
        BugFile bugFile = this.getBugFileService().selectById(id);
        //File file=new File(path);
        //获得请求文件名
        String filename = bugFile.getFileName();
        System.out.println(filename);
        //设置文件MIME类型
        response.setContentType(request.getSession().getServletContext().getMimeType(filename));
        //设置Content-Disposition
        filename = URLEncoder.encode(filename,"UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        response.setContentType("application/x-msdownload;");
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = bugFile.getFilePath();
        //System.out.println(fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }
        in.close();
        out.close();
    }


    @RequestMapping(value = "/demand/{i}")
    public void demandDown(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "i") Long id, Model model) throws IOException {
        DemandFile demandFile = this.getDemandFileService().selectById(id);
        //File file=new File(path);
        //获得请求文件名
        String filename = demandFile.getFileName();
        System.out.println(filename);
        //设置文件MIME类型
        response.setContentType(request.getSession().getServletContext().getMimeType(filename));
        //设置Content-Disposition
        filename = URLEncoder.encode(filename,"UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        response.setContentType("application/x-msdownload;");
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = demandFile.getFilePath();
        //System.out.println(fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }
        in.close();
        out.close();
    }

    @RequestMapping(value = "/programFile/{i}")
    public void programFile(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "i") Long id, Model model) throws IOException {
        ProgramFile programFile = this.getProgramFileService().selectById(id);
        //File file=new File(path);
        //获得请求文件名
        String filename = programFile.getFileName();
        System.out.println(filename);
        //设置文件MIME类型
        response.setContentType(request.getSession().getServletContext().getMimeType(filename));
        //设置Content-Disposition
//        filename = URLEncoder.encode(filename,"UTF-8");
        filename = new String(filename.getBytes(),"ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename="+filename);
        response.setContentType("application/x-msdownload;");
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = programFile.getFilePath();
        //System.out.println(fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();
        //写文件
        int b;
        while((b=in.read())!= -1)
        {
            out.write(b);
        }
        in.close();
        out.close();
    }

    @RequestMapping(value = "/productLogo/{i}")
    public void productLogo(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "i") Long id, Model model) throws IOException {
        Product product = this.getProductService().selectById(id);
        if (product != null && StringUtils.isNotBlank(product.getLogo())){
            //获得请求文件名
            String [] filePathStr = product.getLogo().split("/");
            String filename = filePathStr[filePathStr.length-1];
            //设置文件MIME类型
            response.setContentType(request.getSession().getServletContext().getMimeType(filename));
            //设置Content-Disposition
            filename = new String(filename.getBytes(),"ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+filename);
            response.setContentType("application/x-msdownload;");
            //读取目标文件，通过response将目标文件写到客户端
            //获取目标文件的绝对路径
            String fullFileName = product.getLogo();
            //读取文件
            InputStream in = new FileInputStream(fullFileName);
            OutputStream out = response.getOutputStream();
            //写文件
            int b;
            while((b=in.read())!= -1)
            {
                out.write(b);
            }
            in.close();
            out.close();
        }
    }

}
