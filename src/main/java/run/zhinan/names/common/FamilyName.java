package run.zhinan.names.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import run.zhinan.names.entity.ChineseCharacter;

import java.util.List;

@Getter
@AllArgsConstructor
public class FamilyName {
    String name;
    boolean isCompound;
    List<ChineseCharacter> characters;
}
