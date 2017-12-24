package vo;

/**
 * Created by py on 2017/10/20.
 */
public class MemberVO {
    /**
     * 编号
     */
    int number;

    /**
     * 姓名
     */
    String name;

    /**
     * 分类（进货商、销售商）
     */
    String memberClass;

    /**
     * 级别（五级，一级普通用户，五级VIP客户）
     */
    int level;


    /**
     * 电话
     */
    String phoneNumber;

    /**
     * 地址
     */
    String address;

    /**
     * 邮编
     */
    String postcode;

    /**
     * 电子邮箱
     */
    String mailAddress;

    /**
     * 应收额度
     */
    double collectionLimit;

    /**
     * 应收
     */
    double collection;



    /**
     * 应付
     */

    double payment;

    /**
     * 默认业务员
     */
    String managePerson;

    public MemberVO() {
    }

    public MemberVO(int number, String name, String memberClass, int level, String phoneNumber,
                    String address, String postcode, String mailAddress, double collectionLimit,
                    double collection, double payment, String managePerson) {
        this.number = number;
        this.name = name;
        this.memberClass = memberClass;
        this.level = level;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.postcode = postcode;
        this.mailAddress = mailAddress;
        this.collectionLimit = collectionLimit;
        this.collection = collection;
        this.payment = payment;
        this.managePerson = managePerson;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberClass() {
        return memberClass;
    }

    public void setMemberClass(String memberClass) {
        this.memberClass = memberClass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public double getCollectionLimit() {
        return collectionLimit;
    }

    public void setCollectionLimit(double collectionLimit) {
        this.collectionLimit = collectionLimit;
    }

    public double getCollection() {
        return collection;
    }

    public void setCollection(double collection) {
        this.collection = collection;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public String getManagePerson() {
        return managePerson;
    }

    public void setManagePerson(String managePerson) {
        this.managePerson = managePerson;
    }
}
