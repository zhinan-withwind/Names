package run.zhinan.names.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.names.entity.Character;

import java.util.List;

@Getter
@AllArgsConstructor
public class FamilyName {
    String name;
    boolean isCompound;
    List<Character> characters;
}
