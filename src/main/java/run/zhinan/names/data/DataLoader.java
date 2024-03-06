package run.zhinan.names.data;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.zhinan.names.entity.ChineseCharacter;
import run.zhinan.names.entity.CompoundFamilyName;
import run.zhinan.names.entity.SingleFamilyName;
import run.zhinan.names.mapper.ChineseCharacterMapper;
import run.zhinan.names.mapper.CompoundFamilyNameMapper;
import run.zhinan.names.mapper.SingleFamilyNameMapper;

@Service
@RequiredArgsConstructor
public class DataLoader {
    private final ChineseCharacterMapper characterMapper;
    private final SingleFamilyNameMapper familyNameMapper;
    private final CompoundFamilyNameMapper compoundFamilyNameMapper;

    public ChineseCharacter loadCharacter(String c) {
        return characterMapper.selectOne(Wrappers.<ChineseCharacter>lambdaQuery().eq(ChineseCharacter::getC, c));
    }

    public SingleFamilyName loadFamilyName(String c) {
        return familyNameMapper.selectOne(Wrappers.<SingleFamilyName>lambdaQuery().eq(SingleFamilyName::getC, c));
    }

    public CompoundFamilyName loadCompoundFamilyName(String name) {
        return compoundFamilyNameMapper.selectOne(Wrappers.<CompoundFamilyName>lambdaQuery().eq(CompoundFamilyName::getName, name));
    }
}
