package weixin.personalredpacket.service.impl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 晓春 on 2016/1/30.
 */
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID=1L;
    private RedpacketStateThread redpacketStateThread;

    private RiddleThread riddleThread;

    private LotteryThread lotteryThread;

    private OneLotteryThread oneLotteryThread;
    public MyServlet(){

    }

    public void init(){
        String str=null;
        if (redpacketStateThread == null) {
            redpacketStateThread=new RedpacketStateThread();
            redpacketStateThread.start();
        }
        if (riddleThread == null) {
            riddleThread=new RiddleThread();
            riddleThread.start();
        }
        if (lotteryThread == null) {
            lotteryThread=new LotteryThread();
            lotteryThread.start();
        }
        if (oneLotteryThread == null) {
            oneLotteryThread=new OneLotteryThread();
            oneLotteryThread.start();
        }
    }

    public void doGet(HttpServletRequest httpservletrequest,HttpServletResponse httpservletreponse) throws ServletException,IOException{

    }

    public void destory(){
        if(redpacketStateThread!=null && redpacketStateThread.isInterrupted()){
            redpacketStateThread.interrupt();
        }
        if(riddleThread!=null && riddleThread.isInterrupted()){
            riddleThread.interrupt();
        }
    }
}
