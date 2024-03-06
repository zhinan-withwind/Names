package run.zhinan.names.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.zhinan.names.common.FamilyName;
import run.zhinan.names.common.GivenName;
import run.zhinan.names.common.Name;
import run.zhinan.names.data.DataLoader;
import run.zhinan.names.entity.ChineseCharacter;
import run.zhinan.names.entity.SingleFamilyName;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NameService {
    private final DataLoader dataLoader;

    private List<ChineseCharacter> buildCharacterList(String name, boolean isFamilyName) {
        List<ChineseCharacter> characters = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            String c = name.substring(i, i + 1);
            ChineseCharacter character = dataLoader.loadCharacter(c);
            if (isFamilyName) {
                SingleFamilyName familyName = dataLoader.loadFamilyName(c);
                if (familyName != null) character.setPinyin(familyName.getPinyin());
            }
            characters.add(character);
        }
        return characters;
    }

    public boolean isCompoundFamilyName(String familyName) {
        return familyName.length() > 1 && dataLoader.loadCompoundFamilyName(familyName) != null;
    }

    public boolean hasCompoundFamilyName(String fullName) {
        return fullName.length() > 2 && isCompoundFamilyName(fullName.substring(0, 2));
    }

    public FamilyName buildFamilyName(String familyName) {
        return new FamilyName(familyName, isCompoundFamilyName(familyName), buildCharacterList(familyName, true));
    }

    public GivenName buildGivenName(String givenName) {
        return new GivenName(givenName, buildCharacterList(givenName, false));
    }

    public Name buildName(String familyName, String givenName) {
        return new Name(buildFamilyName(familyName), buildGivenName(givenName));
    }

    public Name buildName(String fullName) {
        int length = hasCompoundFamilyName(fullName) ? 2 : 1;
        return buildName(fullName.substring(0, length), fullName.substring(length));
    }
}
