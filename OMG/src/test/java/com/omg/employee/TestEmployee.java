package com.omg.employee;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.omg.cmn.Search;
import com.omg.employee.domain.EmployeeVO;
import com.omg.employee.service.EmployeeService;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)//스프랭 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml",
                                 "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"		
})
public class TestEmployee {
	final static Logger   LOG = LoggerFactory.getLogger(TestEmployee.class);
	
    @Autowired //테스트 컨텍스트 프레임워크는 일치하는 컨텍스트를 찾아 DI해 준다.
    WebApplicationContext  context ;
    
    @Autowired
    EmployeeService employeeDao;
    
    EmployeeVO employee01;
    EmployeeVO employee02;
    EmployeeVO employee03;  
    EmployeeVO employee04;
    EmployeeVO employee05;
    
    @Test
    public void doSelectList() {
    	//1.all삭제
		//2.3건 입력
		//3.조회
		//4.3건비교
    	
    	//1.
    	this.employeeDao.doDeleteAll();
    	
    	//2.
    	int flag=employeeDao.doInsert(employee01);
    	assertThat(flag, is(1));
		flag=employeeDao.doInsert(employee02);
		assertThat(flag, is(1));
		flag=employeeDao.doInsert(employee03);
		assertThat(flag, is(1));
		
		//3.
		Search search=new Search("10","유비",3,1);
		search.setDiv("20");
		
		List<EmployeeVO> list=employeeDao.doSelectList(search);
		assertThat(list.size(), is(3));
    	
    }
    
    @Test
    @Ignore
    public void passwdConfirm() throws ClassNotFoundException   {
    	LOG.debug("++++++++++++++++++++");
		LOG.debug("++idConfirm()++");
		LOG.debug("++++++++++++++++++++");
    	int cnt=employeeDao.passwdConfirm(employee01);
    	//1(비밀번호 일치)/0(비밀번호 불일치)
    	LOG.debug("=비밀번호 확인="+cnt);
    }
    
    @Test
    @Ignore
    public void idConfirm() throws ClassNotFoundException {
    	LOG.debug("++++++++++++++++++++");
		LOG.debug("++idConfirm()++");
		LOG.debug("++++++++++++++++++++");
    	int cnt=employeeDao.idConfirm(employee02);
    	
    	//1(존재)/0(존재하지 않는 아이디)
    	LOG.debug("=cnt="+cnt);
    }
    
    @Test
    @Ignore
    public void getAll() {
    	//1.all삭제
		//2.3건 입력
		//3.조회
		//4.3건비교
    	
    	//1.
    	this.employeeDao.doDeleteAll();
    	
    	//2.
    	int flag=employeeDao.doInsert(employee01);
    	assertThat(flag, is(1));
		flag=employeeDao.doInsert(employee02);
		assertThat(flag, is(1));
		flag=employeeDao.doInsert(employee03);
		assertThat(flag, is(1));
    	
		//전체 데이터 조회: 3건 확인
    	EmployeeVO cntEmployee=new EmployeeVO();
    	cntEmployee.setEmployee_id("ID");
    	
    	List<EmployeeVO> list=employeeDao.doSelectAll(cntEmployee);
    	assertThat(list.size(), is(3));
    	
    }
    
    @Test 
    @Ignore
    public void doUpdate() {
    	//1.삭제
		//2.조회(seq 찾기)
		//3.수정
		//3.조회(seq 찾기)
		//5.비교
    	
    	//1.
    	employeeDao.doDeleteAll();
    	
    	//1.1
    	int flag=employeeDao.doInsert(employee01);
    	assertThat(flag, is(1));
		
    	//2.
    	EmployeeVO searchEmployee=employeeDao.doSelectOne(employee01);
    	searchEmployee.setPassword(searchEmployee.getPassword()+"_U");
    	searchEmployee.setDept_no(999);
    	searchEmployee.setPosition_no(999);
    	searchEmployee.setCell_phone(999);
    	searchEmployee.setEmail(searchEmployee.getEmail()+"_U");
    	searchEmployee.setAddress(searchEmployee.getAddress()+"_U");
    	searchEmployee.setHoliday(999);
    	searchEmployee.setImg_code(searchEmployee.getImg_code()+"_U");
    	
    	//3.
    	flag=employeeDao.doUpdate(searchEmployee);
    	assertThat(flag, is(1));
    	
    	//4.
    	EmployeeVO vsEmployee=employeeDao.doSelectOne(searchEmployee);
    	
    	//5.
    	this.checkUser(searchEmployee, vsEmployee);
    }
    
    @Test
    @Ignore 
    public void addAndGet() {
    	//기존 데이터 삭제
    	employeeDao.doDelete(employee01);
    	employeeDao.doDelete(employee02);
    	employeeDao.doDelete(employee03);
    	
    	//추가
    	int flag=employeeDao.doInsert(employee01);
    	assertThat(flag, is(1));
    	
    	EmployeeVO cntEmployee=new EmployeeVO();
    	cntEmployee.setEmployee_id("ID");
    	
    	//등록건수조회
//    	int cnt=employeeDao.count(cntEmployee);
//    	assertThat(cnt, is(1));
    	
    	flag=employeeDao.doInsert(employee02);
    	assertThat(flag, is(1));
    	
//    	cnt=employeeDao.count(cntEmployee);
//    	assertThat(cnt, is(2));
    	
    	flag=employeeDao.doInsert(employee03);
    	assertThat(flag, is(1));
    	
//    	cnt=employeeDao.count(cntEmployee);
//    	assertThat(cnt, is(3));
    	
    	//단건조회
    	EmployeeVO vsUser01=employeeDao.doSelectOne(employee01);
    	EmployeeVO vsUser02=employeeDao.doSelectOne(employee02);
    	EmployeeVO vsUser03=employeeDao.doSelectOne(employee03);
    	//조회 데이터 검증
    	checkUser(employee01, vsUser01);
    	checkUser(employee02, vsUser02);
    	checkUser(employee03, vsUser03);
    	
    	
    }
    
    private void checkUser(EmployeeVO orgEmployee, EmployeeVO vsEmployee) {
    	assertThat(orgEmployee.getEmployee_id(),is(vsEmployee.getEmployee_id()));
    	assertThat(orgEmployee.getPassword(),is(vsEmployee.getPassword()));
    	assertThat(orgEmployee.getName(), is(vsEmployee.getName()));
    	assertThat(orgEmployee.getDept_no(), is(vsEmployee.getDept_no()));
    	assertThat(orgEmployee.getPosition_no(), is(vsEmployee.getPosition_no()));
    	assertThat(orgEmployee.getCell_phone(), is(vsEmployee.getCell_phone()));
    	assertThat(orgEmployee.getEmail(),is(vsEmployee.getEmail()));
    	assertThat(orgEmployee.getAddress(), is(vsEmployee.getAddress()));
    	assertThat(orgEmployee.getHoliday(), is(vsEmployee.getHoliday()));
    	assertThat(orgEmployee.getImg_code(), is(vsEmployee.getImg_code()));
    	
    }
    
    
	@Before
	public void setUp() throws Exception {
		LOG.debug("++++++++++++++++++++");
		LOG.debug("++context++"+context);
		LOG.debug("++employeeDao++"+employeeDao);
		LOG.debug("++++++++++++++++++++");
		//"아이디","비미번호","이름","부서번호","직급번호","휴대폰","이메일","주소","입사일","생일","휴가일","이미지코드"
		employee01=new EmployeeVO("ID01","123456","유비_01",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1");     
		employee02=new EmployeeVO("ID02","123456","유비_02",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1");     
		employee03=new EmployeeVO("ID03","123456","유비_03",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1");     
		//없는 아이     
		employee04=new EmployeeVO("noId","123456","유비_02",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1");
		//틀린 비
		employee05=new EmployeeVO("ID03","123456_wrong","유비_03",1,"부서명","직급",1,123456789,"casse2045@naver.com","서울","20/10/28","201029",15,"1","1","1");				
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	@Ignore
	public void doInsert() {
		int flag=employeeDao.doInsert(employee01);
		assertThat(flag, is(1));
	}
	
	@Test
	@Ignore
	public void doDelete() {
		int flag=employeeDao.doDelete(employee01);
		assertThat(flag, is(1));
	}
	
	@Test
	@Ignore
	public void doSelectOne() {
		EmployeeVO outVO=employeeDao.doSelectOne(employee01);
		
		checkUser(employee01, outVO);
		
		
	}

}
