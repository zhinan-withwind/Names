package run.zhinan.names.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import run.zhinan.names.entity.ChineseCharacter;

@Mapper
public interface ChineseCharacterMapper extends BaseMapper<ChineseCharacter>{
}
