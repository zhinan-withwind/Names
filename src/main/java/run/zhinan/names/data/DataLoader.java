package run.zhinan.names.data;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.zhinan.names.entity.Character;
import run.zhinan.names.entity.CompoundFamilyName;
import run.zhinan.names.entity.FamilyName;
import run.zhinan.names.mapper.CharacterMapper;
import run.zhinan.names.mapper.CompoundFamilyNameMapper;
import run.zhinan.names.mapper.FamilyNameMapper;

@Service
@RequiredArgsConstructor
public class DataLoader {
    private final CharacterMapper characterMapper;
    private final FamilyNameMapper familyNameMapper;
    private final CompoundFamilyNameMapper compoundFamilyNameMapper;

    public Character loadCharacter(String c) {
        return characterMapper.selectOne(Wrappers.<Character>lambdaQuery().eq(Character::getC, c));
    }

    public FamilyName loadFamilyName(String c) {
        return familyNameMapper.selectOne(Wrappers.<FamilyName>lambdaQuery().eq(FamilyName::getC, c));
    }

    public CompoundFamilyName loadCompoundFamilyName(String name) {
        return compoundFamilyNameMapper.selectOne(Wrappers.<CompoundFamilyName>lambdaQuery().eq(CompoundFamilyName::getName, name));
    }
}
