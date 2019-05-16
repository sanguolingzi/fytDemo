package fyt.business.controller;

import fyt.business.controller.base.BaseExecutor;
import fyt.business.core.listener.SpringContextUtil;
import fyt.business.pojo.UserPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@RestController()
@RequestMapping("verify")
public class VerifyController {
    private static String codeChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 产生随机的颜色
     * @param minColor
     * @param maxColor
     * @return
     */
    private static Color getRandomColor(int minColor, int maxColor)
    {
        Random r = new Random();
        int red = minColor + r.nextInt(maxColor-minColor);
        int green = minColor + r.nextInt(maxColor-minColor);
        int blue = minColor + r.nextInt(maxColor-minColor);
        return new Color(red,green,blue);
    }

    @RequestMapping(value="verify.do")
    public void Verifyimg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //关闭客户端浏览器的缓冲区。
        response.setHeader("ragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expirse", "0");
        //设置图形大小。
        int width = 90 , height = 20;
        //建立图形缓冲区。
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();//获得	Graphics 对象。
        g.setColor(getRandomColor(180, 250));//设置背景色。
        g.fillRect(0, 0, width, height);//填充背景。

        StringBuilder validationCode = new StringBuilder();//用于保存最后的验证码
        String[] fontNames = {"Times New Roman","Book antiqua","Arial"};//用于随机的字体的集合

        Random r = new Random();
        //随机生成3-5个验证码
        for(int i = 0; i<3+r.nextInt(3); i++)
        {
            g.setFont(new Font(fontNames[r.nextInt(3)] , Font.ITALIC , height));
            char codeChar = codeChars.charAt(r.nextInt(codeChars.length()));
            validationCode.append(codeChar);
            g.setColor(getRandomColor(10, 100));
            g.drawString(String.valueOf(codeChar), 16 * i+ r.nextInt(7) ,height - r.nextInt(6));//在图形上输出验证码
        }
        //随机生干扰码
        for(int i = 0; i<30 ; i++)
        {
            g.setColor(getRandomColor(90, 200));
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            g.drawLine(x,y , x+r.nextInt(10), y+r.nextInt(5));
        }

        HttpSession session = request.getSession();//得到HttpSession对象
        session.setMaxInactiveInterval(10*60);//设置session对象10分钟失效。
        session.setAttribute("validation_code", validationCode.toString());//将验证码保存在session中
        g.dispose();//关闭Graphics对象
        OutputStream os = response.getOutputStream();//得到输出流
        ImageIO.write(image, "JPEG", os);//以JPEG格式向客户端发送图形验证码
    }
}
