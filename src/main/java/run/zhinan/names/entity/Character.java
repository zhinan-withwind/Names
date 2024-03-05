package run.zhinan.names.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("characters")
public class Character {
    Long id;
    String unicode;
    String c;
    String traditional;
    int strokeNum;
    int s_strokeNum;
    int t_strokeNum;
    int n_strokeNum;
    String pinyin;
    String pinyins;
    String radical;
    String radicals;
    String element;
    String elements;
    int structureType;
}
