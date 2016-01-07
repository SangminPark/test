package best.prog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import best.prog.domain.Code;
import best.prog.repository.CodeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-appConfig.xml")
@Transactional
public class CodeServiceTest {

    @Autowired CodeService codeService;
    @Autowired CodeRepository codeRepository;

    
    @PostConstruct
    public void setUp() {
      codeRepository.deleteAllInBatch();
      
      int index = 0;
      Code code = new Code();
      code.setCode("group_code_" + index);
      code.setName("group_name_" + index);
      Code code1 = new Code();
      code1.setCode("code_child1");
      code1.setName("name_child1");
      code1.setParentCode(code);
      Code code2 = new Code();
      code2.setCode("code_child2");
      code2.setName("name_child2");
      code2.setParentCode(code);
      
      code.getChildCodes().add(code1);
      code.getChildCodes().add(code2);
      codeRepository.save(code);
      
      index++;
      code = new Code();
      code.setCode("group_code_" + index);
      code.setName("group_name_" + index);
      codeRepository.save(code);
      index++;
      code = new Code();
      code.setCode("group_code_" + index);
      code.setName("group_name_" + index);
      codeRepository.save(code);
    }

    @Test
    public void 하위코드_생성() throws Exception {

      Code pcode = codeRepository.findByCode("group_code_0");
      
      Code code = new Code();
      code.setCode("code_child");
      code.setName("name_child");
      code.setParentCode(pcode);

      codeService.create(code);
      
      Code childCode = codeService.findCode(code);
      assertNotNull(childCode);
    }

    @Test
    public void 그룹코드에_여러하위코드_추가() throws Exception {

      Code pcode = codeRepository.findByCode("group_code_0");
      codeService.findOneWithSubCode(pcode);

      Code code1 = new Code();
      code1.setCode("code_child3");
      code1.setName("name_child3");
      code1.setParentCode(pcode);
      
      Code code2 = new Code();
      code2.setCode("code_child4");
      code2.setName("name_child4");
      code2.setParentCode(pcode);
      
      pcode.getChildCodes().add(code1);
      pcode.getChildCodes().add(code2);
      
      codeService.update(pcode);
      
      Code resultCode = codeService.findOneWithSubCode(pcode);
      assertEquals(4, resultCode.getChildCodes().size());
    }

    @Test
    public void 그룹코드에_종속된_하위코드들중에서_특정하위코드_삭제() throws Exception {

      Code pcode = codeRepository.findByCode("group_code_0");
      
      Code code1 = new Code();
      code1.setCode("code_child3");
      code1.setName("name_child3");
      code1.setParentCode(pcode);
      
      Code code2 = new Code();
      code2.setCode("code_child4");
      code2.setName("name_child4");
      code2.setParentCode(pcode);
      
      pcode.getChildCodes().add(code1);
      pcode.getChildCodes().add(code2);
      
      codeService.update(pcode);
      
      Code resultCode = codeService.findOneWithSubCode(pcode);
      assertEquals(4, resultCode.getChildCodes().size());
      
      //삭제
      Code childCode = codeRepository.findByCode("code_child3");
      resultCode.getChildCodes().remove(childCode);
      resultCode = codeService.findOneWithSubCode(resultCode);
      assertEquals(3, resultCode.getChildCodes().size());
    }

    @Test
    public void 코드_수정() throws Exception {

      Code code = codeRepository.findByCode("group_code_0");
      code.setName("admin1");
      
      
      Code updatedCode = codeService.update(code);
      
      
      assertEquals(code, updatedCode);
    }

    @Test(expected = IllegalStateException.class)
    public void 코드_생성_코드중복_예외() throws Exception {
        Code code1 = new Code();
        code1.setCode("code1");
        code1.setName("name1");
        Code code2 = new Code();
        code2.setCode("code1");
        code2.setName("name2");
        

        codeService.create(code1);
        codeService.create(code2);


        fail("예외가 발생해야 한다.");
    }


}