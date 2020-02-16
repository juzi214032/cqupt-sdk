package com.github.juzi214032.cqupt.sdk.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则工具
 *
 * @author Juzi - https://juzibiji.top
 * @since 2019/8/2 19:28
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
