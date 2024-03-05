package run.zhinan.names.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("compound_family_names")
public class CompoundFamilyName {
    Long id;
    String name;
}
