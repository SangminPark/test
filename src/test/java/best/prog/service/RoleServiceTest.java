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

import best.prog.domain.Role;
import best.prog.repository.RoleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-appConfig.xml")
@Transactional
public class RoleServiceTest {

    @Autowired RoleService roleService;
    @Autowired RoleRepository roleRepository;

    int index = 0;
    public Role createRole() throws Exception {
      Role role = new Role();
      role.setName("admin_" + index);
      role = roleService.create(role);
      index++;
      return role;
    }

    @Test
    public void 롤_생성() throws Exception {

        Role role = createRole();

        Role findRole = roleService.findRole(role);
        assertNotNull(findRole);
    }

    @Test
    public void 롤_수정() throws Exception {

      Role role = createRole();
        role.setName("admin1");
        
        
        Role updatedRole = roleService.update(role);
        
        
        assertEquals(role, updatedRole);
    }

    @Test(expected = IllegalStateException.class)
    public void 롤_생성_명칭중복_예외() throws Exception {
        Role role1 = new Role();
        role1.setName("g1");
        Role role2 = new Role();
        role2.setName("g1");
        

        roleService.create(role1);
        roleService.create(role2);


        fail("예외가 발생해야 한다.");
    }


}