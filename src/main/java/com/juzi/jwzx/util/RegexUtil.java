package com.juzi.jwzx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具
 *
 * @author Juzi
 * @date 2019/8/2 19:28
 * Blog https://juzibiji.top
 */
public class RegexUtil {
    public static List<String> parse(String regex, String source) {
        List<String> stringList = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                stringList.add(matcher.group(i));
            }
        }
        return stringList;
    }
    
    public static List<String> parse(Pattern pattern, String source) {
        List<String> stringList = new ArrayList<>();
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                stringList.add(matcher.group(i));
            }
        }
        return stringList;
    }
    
}
