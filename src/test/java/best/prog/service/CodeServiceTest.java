package best.prog.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

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

    int index = 0;
    public Code createCode() throws Exception {
      Code code = new Code();
      code.setCode("code_" + index);
      code.setName("name_" + index);
      code = codeService.create(code);
      index++;
      return code;
    }

    @Test
    public void 그룹코드_생성() throws Exception {

        Code code = createCode();

        Code findCode = codeService.findCode(code);
        assertNotNull(findCode);
    }

    @Test
    public void 하위코드_생성() throws Exception {

      Code pcode = createCode();
      Code findCode = codeService.findCode(pcode);
      
      Code code = new Code();
      code.setCode("code_child");
      code.setName("name_child");
      code.setParentCode(findCode);

      code = codeService.create(code);
      
      Code childCode = codeService.findCode(code);
      assertNotNull(childCode);
    }

    @Test
    public void 그룹코드에_여러하위코드_추가() throws Exception {

      Code pcode = createCode();
      codeService.findCode(pcode);
      
      Code code1 = new Code();
      code1.setCode("code_child1");
      code1.setName("name_child1");
      pcode.getChildCodes().add(code1);
      code1.setParentCode(pcode);
      
      Code code2 = new Code();
      code2.setCode("code_child2");
      code2.setName("name_child2");
      pcode.getChildCodes().add(code2);
      code2.setParentCode(pcode);
      
      codeService.create(code1);
      codeService.create(code2);
      
      Code resultCode = codeService.findOneWithSubCode(pcode);
      for(Code c : resultCode.getChildCodes()) {
        System.out.println(c.getCode());
      }
      assertEquals(2, resultCode.getChildCodes().size());
    }

    @Test
    public void 코드_수정() throws Exception {

      Code code = createCode();
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