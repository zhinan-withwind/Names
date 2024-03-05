package run.zhinan.names.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("family_names")
public class FamilyName {
    Long id;
    String c;
    String pinyin;
}
