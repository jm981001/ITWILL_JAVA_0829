package covariant;

public class Ex2 {

	public static void main(String[] args) {

		Customer cus = new Customer();			// 일반고객
		VipCustomer vip = new VipCustomer();	// vip고객
		
		Employee emp = new Employee();			// 일반직원
		VipEmployee vipEmp = new VipEmployee();	// vip직원
		
		// 1. 공변 리턴 타입 적용 X
		cus.setAcc(emp.getAccount()); // 일반고객이 일반계좌를 개설
//		vip.setItwillBank(vipEmp.getAccount());
		// => vip 고객의 멤버변수는 itwillBank 타입이므로
		//	  vip 직원에게 개설한 계좌를 다운캐스팅 하여 전달해야한다!
		vip.setItwillBank((ItwillBank)vipEmp.getAccount());
		// => 하지만 이런 서브클래스가 (ItwillBank) 굉장히 많다면?
		//    매번 instanceof로 객체 확인 후 다운캐스팅을 통해 사용해야한다.
		//    즉, 공변 리턴 타입이 없다면 사용하는 입장에서 매번 인스턴스 체크와 다운캐스팅을 해야하는 번거로움이 발생!
		
		// 2. 공변 리턴 타입 적용 O
		vip.setItwillBank(vipEmp.getAccount());
		
		
	}

}

class Customer {
	private Account acc;
	private int money; // 현금
	String name;
	
	// Alt + Shift + S -> R (Get/Set 메서드 단축키)
	public Account getAcc() {
		return acc;
	}
	public void setAcc(Account acc) {
		this.acc = acc;
	}
}

class VipCustomer extends Customer {
	private int grade;
	private ItwillBank itwillBank;
	
	public ItwillBank getItwillBank() {
		return itwillBank;
	}
	public void setItwillBank(ItwillBank itwillBank) {
		this.itwillBank = itwillBank;
	}
}

// 일반 직원
class Employee {
	// 일반 계좌를 개설
	public Account getAccount() {
		return new Account();
	}
}

// Vip직원
class VipEmployee extends Employee {

	// 1. 공변 리턴 타입 적용 X
	@Override
	public ItwillBank getAccount() {
		return new ItwillBank();
	}
	
	// 2. 공변 리턴 타입 적용 O
//	@Override
//	public ItwillBank getAccount() {
//		return new ItwillBank();
//	}
}

class Account {
	// ... 생략
}

class ItwillBank extends Account {
	// ... 생략
}




