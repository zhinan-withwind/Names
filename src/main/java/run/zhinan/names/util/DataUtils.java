package run.zhinan.names.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataUtils {
    private final static String url = "jdbc:mysql://localhost:3306/names?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private final static String user = "root";
    private final static String password = "FORget2011";

    @SneakyThrows
    public static void loadCharacterDataFromJson() {
        Connection connection = DriverManager.getConnection(url, user, password);
        String insertSql = "INSERT INTO characters (unicode, c, pinyin) VALUES (?, ?, ?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSql);

        // 将 JSON 数据插入数据表
        String jsonFilePath = "src/main/resources/data/characters.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        JSONArray data = JSON.parseArray(jsonContent);
        int result = 0;
        for (Object o : data) {
            JSONObject character = (JSONObject) o;
            insertStatement.setInt(1, character.getString("c").codePointAt(0));
            insertStatement.setString(2, character.getString("c"));
            insertStatement.setString(3, character.getString("pinyin"));
            result += insertStatement.executeUpdate();
        }
        System.out.println("插入了 " + result + " 条数据");
        insertStatement.close();
        connection.close();
    }

    @SneakyThrows
    public static void loadFamilyNameFromFile() {
        Connection connection = DriverManager.getConnection(url, user, password);
        String insertSql = "INSERT INTO family_names (c) VALUES (?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSql);

        // 从文件中读取数据
        String filePath = "src/main/resources/data/Chinese_Family_Name.txt";
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] names = content.split("\r\n");
        List<String> complexNames = new ArrayList<>();
        for (String name : names) {
            if (name.length() > 1) {
                complexNames.add(name);
            } else {
                insertStatement.setString(1, name);
                insertStatement.addBatch();
            }
        }
        System.out.println("插入了 " + insertStatement.executeBatch().length + " 条单姓数据");
        for (String name : complexNames) {
            insertStatement.setString(1, name);
            insertStatement.addBatch();
        }
        System.out.println("插入了 " + insertStatement.executeBatch().length + " 条复姓数据");

        insertStatement.close();
        connection.close();
    }

    @SneakyThrows
    public static void loadFamilyNamePinYinFromJson() {
        Connection connection = DriverManager.getConnection(url, user, password);
        String insertSql = "UPDATE family_names SET pinyin=? WHERE c=?;";
        PreparedStatement updateStatement = connection.prepareStatement(insertSql);

        // 将 JSON 数据插入数据表
        String jsonFilePath = "src/main/resources/data/familyNameData.json";
        String jsonContent = new String(Files.readAllBytes(Paths.get(jsonFilePath)));
        JSONArray data = JSON.parseArray(jsonContent);
        for (Object o : data) {
            JSONObject character = (JSONObject) o;
            updateStatement.setString(1, character.getString("pinYin"));
            updateStatement.setString(2, character.getString("character"));
            updateStatement.addBatch();
        }
        System.out.println("更新了 " + updateStatement.executeBatch().length + " 条数据");
        updateStatement.close();
        connection.close();
    }

    @SneakyThrows
    public static void loadCompoundFamilyNames() {
        String[] data = {"爱新", "百里", "北堂", "北野", "北宫", "辟闾", "孛尔", "淳于", "成公", "陈生", "褚师", "端木", "东方", "东郭", "东野", "东门", "第五", "大狐", "段干", "段阳", "带曰", "第二", "东宫", "公孙", "公冶", "公羊", "公良", "公西", "公孟", "高堂", "高阳", "公析", "公肩", "公坚", "郭公", "谷梁", "毌将", "公乘", "毌丘", "公户", "公广", "公仪", "公祖", "皇甫", "黄龙", "胡母", "何阳", "夹谷", "九方", "即墨", "觉罗", "梁丘", "闾丘", "洛阳", "陵尹", "冷富", "龙丘", "令狐", "林彭", "南宫", "南郭", "女娲", "南伯", "南容", "南门", "南野", "欧阳", "欧侯", "濮阳", "青阳", "漆雕", "亓官", "渠丘", "壤驷", "上官", "少室", "少叔", "司徒", "司马", "司空", "司寇", "士孙", "申屠", "申徒", "申鲜", "申叔", "夙沙", "叔先", "叔仲", "侍其", "叔孙", "澹台", "太史", "太叔", "太公", "屠岸", "唐古", "闻人", "巫马", "微生", "王孙", "无庸", "夏侯", "西门", "信平", "鲜于", "轩辕", "相里", "新垣", "徐离", "羊舌", "羊角", "延陵", "於陵", "伊祁", "吾丘", "乐正", "只斤", "诸葛", "颛孙", "仲孙", "仲长", "钟离", "宗政", "主父", "中叔", "左人", "左丘", "宰父", "长儿", "仉督", "单于", "叱干", "叱利", "车非", "车公", "车侯", "车长", "车绵", "独孤", "大野", "独吉", "达奚", "达官", "达仲", "达品", "哥舒", "哥夜", "哥翰", "哥汗", "赫连", "呼延", "贺兰", "黑齿", "斛律", "斛粟", "贺若", "贺奴", "贺远", "贺元", "吉胡", "吉利", "吉家", "可频", "慕容", "万俟", "万红", "万中", "抹捻", "纳兰", "纳西", "纳吉", "纳罕", "纳塞", "纳博", "纳称", "纳勉", "普周", "仆固", "仆散", "蒲察", "屈突", "屈卢", "钳耳", "是云", "索卢", "厍狄", "拓跋", "同蹄", "秃发", "完颜", "完明", "完忠", "宇文", "尉迟", "耶律", "耶红", "也先", "耶鲜", "耶闻", "长孙", "长南", "长北", "长西", "长红", "长元", "长秋", "长寸", "长李", "长云", "白何", "常夏", "陈梁", "陈林", "曹岳", "邓李", "二胡", "范姜", "郭罗", "高陈", "胡杨", "黄方", "贺陈", "蒋申", "刘付", "刘谭", "陆费", "陆叶", "钱王", "钱赖", "石邵", "巫许", "吴刘", "吴沈", "王曹", "有琴", "张包", "张简", "张廖", "钟任", "章项"};
        Connection connection = DriverManager.getConnection(url, user, password);
        String selectSql = "SELECT * FROM compound_family_names WHERE name=?;";
        String insertSql = "INSERT INTO compound_family_names (name, c1_id, c2_id, pinyin) VALUES (?, ?, ?, ?);";
        String querySql  = "SELECT id, pinyin FROM characters where c=?;";
        PreparedStatement selectStatement = connection.prepareStatement(selectSql);
        PreparedStatement insertStatement = connection.prepareStatement(insertSql);
        PreparedStatement queryStatement  = connection.prepareStatement( querySql);
        for (String name : data) {
            selectStatement.setString(1, name);
            if (selectStatement.executeQuery().next()) {
                continue;
            }
            insertStatement.setString(1, name);
            queryStatement.setString(1, name.substring(0, 1));
            ResultSet rs1 = queryStatement.executeQuery();
            String pinyin;
            if (rs1.next()) {
                insertStatement.setInt(2, rs1.getInt("id"));
                pinyin = rs1.getString("pinyin") + " ";
            } else {
                continue;
            }
            queryStatement.setString(1, name.substring(1));
            ResultSet rs2 = queryStatement.executeQuery();
            if (rs2.next()) {
                insertStatement.setInt(3, rs2.getInt("id"));
                pinyin += rs2.getString("pinyin");
            } else {
                continue;
            }
            insertStatement.setString(4, pinyin);
            insertStatement.addBatch();
        }
        System.out.println("插入了 " + insertStatement.executeBatch().length + " 条复姓数据");

        selectStatement.close();
        insertStatement.close();
        queryStatement.close();
        connection.close();
    }

    public static void main(String[] args) {
//        loadCharacterDataFromJson();
//        loadFamilyNameFromFile();
//        loadFamilyNamePinYinFromJson();
        loadCompoundFamilyNames();
    }
}
