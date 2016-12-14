package weixin.util;

import jodd.io.StreamUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by 晓春 on 2016/5/16.
 */
public class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] body = null;
    private HttpServletRequest requestInner;
    private Map<String, String[]> parameters = new HashMap<String, String[]>();

    public MyHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.requestInner = request;
        body = StreamUtil.readBytes(request.getInputStream());
        if (null != body && body.length > 0) {
            if (StringUtils.contains(request.getContentType(), "application/x-www-form-urlencoded")) {
                String bodyString = new String(body);
                String[] split = StringUtils.split(bodyString, "&");
                for (String kv : split) {
                    String[] keyValues = kv.split("=");
                    for (int i = 0; i < keyValues.length; i++) {
                        keyValues[i] = URLDecoder.decode(keyValues[i], "UTF-8");
                    }
                    parameters.put(keyValues[0], Arrays.copyOfRange(keyValues, 1, keyValues.length));
                }
            }
        }
        this.parameters.putAll(requestInner.getParameterMap());
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public String getParameter(String name) {
        if (null != name && this.parameters.containsKey(name)) {
            String[] strings = parameters.get(name);
            return null != strings && strings.length > 0 ? strings[0] : null;
        }
        return null;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(this.parameters.keySet());
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameters;
    }

    @Override
    public String[] getParameterValues(String name) {
        return parameters.get(name);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }

            @Override
            public int read() throws IOException {
                return in.read();
            }
        };
    }


}
