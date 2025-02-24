import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    @Test
    public void test() {
        String input = "var='add_uy' var_name='添加uy' var='add_uy' var_name='添加uy'" +
                " var='add_uym' var_name='添加uy' " +
                "var='add_uya' var_name='添加uy' var='add_uy' var_name='添加uy' " +
                "var='add_uyc' var_name";
        List<Map<String, String>> entries = extractKeyValuePairs(input);

        // 打印结果
        for (Map<String, String> entry : entries) {
            System.out.println("var: " + entry.get("var") + ", var_name: " + entry.get("var_name"));
        }
    }

    public static List<Map<String, String>> extractKeyValuePairs(String input) {
        List<Map<String, String>> entries = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b(var|var_name)='([^']*)'");
        Matcher matcher = pattern.matcher(input);

        Map<String, String> currentEntry = null;
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            if ("var".equals(key)) {
                currentEntry = new HashMap<>();
                currentEntry.put("var", value);
            } else if ("var_name".equals(key) && currentEntry != null) {
                currentEntry.put("var_name", value);
                entries.add(currentEntry);
                currentEntry = null; // 重置以等待下一个var
            }
        }

        return entries;
    }
}
