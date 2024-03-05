package run.zhinan.names.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import run.zhinan.names.common.FamilyName;
import run.zhinan.names.common.GivenName;
import run.zhinan.names.common.Name;
import run.zhinan.names.data.DataLoader;
import run.zhinan.names.entity.Character;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NameService {
    private final DataLoader dataLoader;

    public boolean isCompoundFamilyName(String name) {
        return name.length() > 1 && dataLoader.loadCompoundFamilyName(name) != null;
    }

    public Name buildName(String name) {
        FamilyName familyName = buildFamilyName(name);
        GivenName  givenName  = buildGivenName(name);
        return new Name(familyName, givenName);
    }

    public FamilyName buildFamilyName(String fullName) {
        boolean isCompound = isCompoundFamilyName(fullName);
        String familyName = isCompound ? fullName.substring(0, 2) : fullName.substring(0, 1);
        return new FamilyName(familyName, isCompound, buildCharacterList(familyName));
    }

    public GivenName buildGivenName(String fullName) {
        boolean isCompound = isCompoundFamilyName(fullName);
        String givenName = fullName.substring(isCompound ? 2 : 1);
        return new GivenName(fullName, buildCharacterList(givenName));
    }

    private List<Character> buildCharacterList(String name) {
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            String c = name.substring(i, i + 1);
            Character character = dataLoader.loadCharacter(c);
            characters.add(character);
        }
        return characters;
    }
}
