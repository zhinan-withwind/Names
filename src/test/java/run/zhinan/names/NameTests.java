package run.zhinan.names;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import run.zhinan.names.common.Name;
import run.zhinan.names.service.NameService;

@SpringBootTest(classes = NamesApplication.class)
@RunWith(SpringRunner.class)
public class NameTests {
    @Autowired
    private NameService nameService;

    @Test
    public void testIsCompoundFamilyName() {
        assert nameService.isCompoundFamilyName("司马");
        assert nameService.isCompoundFamilyName("诸葛");
        assert !nameService.isCompoundFamilyName("赵");
    }

    @Test
    public void testBuildName() {
        Name name = nameService.buildName("司马相如");
        assert name.getFamilyName().getName().equals("司马");
        assert name.getGivenName ().getName().equals("相如");
        assert name.getFamilyName().isCompound();
        assert name.getGivenName().getCharacters().size() == 2;

        name = nameService.buildName("赵云");
        assert name.getFamilyName().getName().equals("赵");
        assert name.getGivenName ().getName().equals("云");
        assert !name.getFamilyName().isCompound();
        assert name.getGivenName().getCharacters().size() == 1;
    }
}
