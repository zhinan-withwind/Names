package run.zhinan.names.common;

import lombok.Getter;
import run.zhinan.names.entity.ChineseCharacter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GivenName {
    String name;
    List<ChineseCharacter> characters;
    List<ChineseCharacter> charactersWithoutDuplicate;

    public GivenName(String name, List<ChineseCharacter> characters) {
        this.name = name;
        this.characters = characters;
        this.charactersWithoutDuplicate = new ArrayList<>();
        for (ChineseCharacter character : characters) {
            if (!charactersWithoutDuplicate.contains(character))
                charactersWithoutDuplicate.add(character);
        }
    }
}
