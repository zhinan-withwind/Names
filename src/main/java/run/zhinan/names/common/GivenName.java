package run.zhinan.names.common;

import lombok.Getter;
import run.zhinan.names.entity.Character;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GivenName {
    String name;
    List<Character> characters;
    List<Character> charactersWithoutDuplicate;

    public GivenName(String name, List<Character> characters) {
        this.name = name;
        this.characters = characters;
        this.charactersWithoutDuplicate = new ArrayList<>();
        for (Character character : characters) {
            if (!charactersWithoutDuplicate.contains(character))
                charactersWithoutDuplicate.add(character);
        }
    }
}
