package Package;

public class Student {
		private String studentName;
		private String addRess;
		private float phone;
		private Account account;
		public String getStudentName() {
			return studentName;
		}
		public void setStudentName(String studentName) {
			this.studentName = studentName;
		}
		public String getAddRess() {
			return addRess;
		}
		public void setAddRess(String addRess) {
			this.addRess = addRess;
		}
		public float getPhone() {
			return phone;
		}
		public void setPhone(float phone) {
			this.phone = phone;
		}
		
		
		public Account getAccount() {
			return account;
		}
		public void setAccount(Account account) {
			this.account = account;
		}
		public Student() {
			super();
		}
		public Student(String studentName, String addRess, float phone, Account account) {
			super();
			this.studentName = studentName;
			this.addRess = addRess;
			this.phone = phone;
			this.account = account;
		}
		@Override
		public String toString() {
			return "Student [studentName=" + studentName + ", addRess=" + addRess + ", phone=" + phone + ", account="
					+ account + "]";
		}
		
		
}
