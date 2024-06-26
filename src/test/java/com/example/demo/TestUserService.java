
package com.example.demo;

import com.example.demo.entity.Address;
import com.example.demo.entity.Social;
import com.example.demo.entity.User;
import com.example.demo.exception.BaseException;
import com.example.demo.exception.UserException;
import com.example.demo.service.AddressService;
import com.example.demo.service.SocialService;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.*;
        import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    @Autowired
    private UserService userService;

    @Autowired
    private SocialService socialService;

    @Autowired
    private AddressService addressService;

    @Order(1)
    @Test
    void testCreate() throws BaseException {
        User user = userService.create(TestData.email, TestData.password, TestData.name);
        //check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());


        //check equal
        Assertions.assertEquals(TestData.name,user.getName());
        Assertions.assertEquals(TestData.email,user.getEmail());
        boolean b = userService.checkPassword(TestData.password, user.getPassword());
        Assertions.assertTrue(b);

    }

    @Order(2)

    @Test
    void testUpdate() throws UserException {
        Optional<User> optional = userService.findByEmail(TestData.email);
        Assertions.assertTrue(optional.isPresent());

        User user = optional.get();
        User updateName = userService.updateName(user.getId(), TestUpdateData.name);

        Assertions.assertNotNull(updateName);
        Assertions.assertEquals(TestUpdateData.name,updateName.getName());


    }

    @Order(3)
    @Test
    void testCreateSocial() {
        Optional<User> optional = userService.findByEmail(TestData.email);
        Assertions.assertTrue(optional.isPresent());

        User user = optional.get();
        Social social = user.getSocial();
        Assertions.assertNull(social);

        social = socialService.create(user, TestSocialData.facebook, TestSocialData.line, TestSocialData.instagram, TestSocialData.tiktok);


        Assertions.assertNotNull(social);
        Assertions.assertEquals(TestSocialData.facebook,social.getFacebook());
    }
    @Order(3)
    @Test
    void testCreateAddress() {
        Optional<User> optional = userService.findByEmail(TestData.email);
        Assertions.assertTrue(optional.isPresent());

        User user = optional.get();
        List<Address> addresses = user.getAddresses();
        Assertions.assertTrue(addresses.isEmpty());

        createAddress(user,TestAddressData.line1,TestAddressData.line2,TestAddressData.zipcode);
        createAddress(user,TestAddressData2.line1,TestAddressData2.line2,TestAddressData2.zipcode);
    }

    private void createAddress(User user,String line1,String line2,String zipcode){
        Address address = addressService.create(user,line1,line2,zipcode);
        Assertions.assertNotNull(address);
        Assertions.assertEquals(line1,address.getLine1());
        Assertions.assertEquals(zipcode,address.getZipcode());
    }

    @Order(4)
    @Test
    void testDelete() {
        Optional<User> optional = userService.findByEmail(TestData.email);
        Assertions.assertTrue(optional.isPresent());

        User user = optional.get();

        //social
        Social social = user.getSocial();
        Assertions.assertNotNull(social);
        Assertions.assertEquals(TestSocialData.facebook,social.getFacebook());

        //address
        List<Address> addresses = user.getAddresses();
        Assertions.assertFalse(addresses.isEmpty());
        Assertions.assertEquals(2,addresses.size());

        userService.delete(user.getId());

        Optional<User> optionalDelete = userService.findByEmail(TestData.email);
        Assertions.assertTrue(optionalDelete.isEmpty());
    }



    interface TestData{

        String email = "pachara@Test";
        String password = "pacharazaza";
        String name="pachara ";
    }

    interface TestUpdateData{

        String name="pachara222 ";
    }

    interface TestSocialData{

        String facebook="pachara pethshee";
        String instagram ="pcrrr ";
        String line="kkk12321 ";
        String tiktok="pcrrrtk ";
    }

    interface TestAddressData{

        String line1="ramkamhang";
        String line2="radpattana 22 ";
        String zipcode="10240 ";
    }
    interface TestAddressData2{

        String line1="ramkamhang2";
        String line2="radpattana 33333333333 ";
        String zipcode="10240333333333 ";
    }

}
