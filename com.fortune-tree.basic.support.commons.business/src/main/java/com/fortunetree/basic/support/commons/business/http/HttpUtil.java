package com.fortunetree.basic.support.commons.business.http;

import com.google.common.collect.Lists;
import org.apache.commons.collections.MapUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Args;

import java.io.*;
import java.util.List;
import java.util.Map;

public class HttpUtil {
    private static final int BUFFER_SIZE = 1024 * 8;

    public static String buildGetParam(String url, Map<String, String> paramMap) {
        Args.notNull(url, "url");
        if(MapUtils.isNotEmpty(paramMap)) {
            List<NameValuePair> paramList = Lists.newArrayListWithCapacity(paramMap.size());
            for (String key : paramMap.keySet()) {
                paramList.add(new BasicNameValuePair(key, paramMap.get(key)));
            }
            //拼接参数
            url += "?" + URLEncodedUtils.format(paramList, Consts.UTF_8);
        }
        return url;
    }

    public static String readStr(BufferedReader reader) throws IOException {

            String str = "";
            StringBuffer buffer = new StringBuffer();
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
        return buffer.toString();
    }

    /**
     * read string.
     *
     * @param reader Reader instance.
     * @return String.
     * @throws IOException
     */
    public static String read(Reader reader) throws IOException
    {
        try(StringWriter writer = new StringWriter())
        {
            write(reader, writer);
            return writer.getBuffer().toString();
        }
    }

    /**
     * write.
     *
     * @param reader Reader.
     * @param writer Writer.
     * @return count.
     * @throws IOException
     */
    public static long write(Reader reader, Writer writer) throws IOException
    {
        return write(reader, writer, BUFFER_SIZE);
    }

    /**
     * write.
     *
     * @param reader Reader.
     * @param writer Writer.
     * @param bufferSize buffer size.
     * @return count.
     * @throws IOException
     */
    public static long write(Reader reader, Writer writer, int bufferSize) throws IOException
    {
        int read;
        long total = 0;
        char[] buf = new char[BUFFER_SIZE];
        while( ( read = reader.read(buf) ) != -1 )
        {
            writer.write(buf, 0, read);
            total += read;
        }
        return total;
    }

}
