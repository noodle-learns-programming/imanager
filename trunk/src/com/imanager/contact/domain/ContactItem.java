package com.imanager.contact.domain;

import java.util.Date;

import com.imanager.common.Basement;

public class ContactItem extends Basement{
	
	private int contactItemId;
	private String name;
	private String pinyin;
	private String englishName;
	private String nickName;
	private String sex;
	private int age;
	private Date birthday;
	private String marriage;
	private String nativePlace;
	private String clCountry;
	private String clProvince;
	private String clCity;
	private String clSubcity;
	private String address;
	private String zip;
	private String school;
	private String schoolAddress;
	private String company;
	private String companyAddress;
	private String mobile1;
	private String mobile2;
	private String mobile1City;
	private String mobile2City;
	private String familyPhone1;
	private String familyPhone2;
	private String familyPhone1City;
	private String familyPhone2City;
	private String workPhone1;
	private String workPhone2;
	private String workPhone1City;
	private String workPhone2City;
	private String email1;
	private String email2;
	private String email1Note;
	private String email2Note;
	private String qq1;
	private String qq2;
	private String qq1Note;
	private String qq2Note;
	private String msn1;
	private String msn2;
	private String msn1Note;
	private String msn2Note;
	private String yahoo1;
	private String yahoo2;
	private String yahoo1Note;
	private String yahoo2Note;
	private String aliwang1;
	private String aliwang2;
	private String aliwang1Note;
	private String aliwang2Note;
	private String web1;
	private String web2;
	private String web1Note;
	private String web2Note;
	private String other1;
	private String other2;
	private String other1Note;
	private String other2Note;
	private String note;
	private ContactType contactType;
	
	private String area;
	
	public int getContactItemId() {
		return contactItemId;
	}
	public void setContactItemId(int contactItemId) {
		this.contactItemId = contactItemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getEnglishName() {
		return englishName;
	}
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getClCountry() {
		return clCountry;
	}
	public void setClCountry(String clCountry) {
		this.clCountry = clCountry;
	}
	public String getClProvince() {
		return clProvince;
	}
	public void setClProvince(String clProvince) {
		this.clProvince = clProvince;
	}
	public String getClCity() {
		return clCity;
	}
	public void setClCity(String clCity) {
		this.clCity = clCity;
	}
	public String getClSubcity() {
		return clSubcity;
	}
	public void setClSubcity(String clSubcity) {
		this.clSubcity = clSubcity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile1City() {
		return mobile1City;
	}
	public void setMobile1City(String mobile1City) {
		this.mobile1City = mobile1City;
	}
	public String getMobile2City() {
		return mobile2City;
	}
	public void setMobile2City(String mobile2City) {
		this.mobile2City = mobile2City;
	}
	public String getFamilyPhone1() {
		return familyPhone1;
	}
	public void setFamilyPhone1(String familyPhone1) {
		this.familyPhone1 = familyPhone1;
	}
	public String getFamilyPhone2() {
		return familyPhone2;
	}
	public void setFamilyPhone2(String familyPhone2) {
		this.familyPhone2 = familyPhone2;
	}
	public String getFamilyPhone1City() {
		return familyPhone1City;
	}
	public void setFamilyPhone1City(String familyPhone1City) {
		this.familyPhone1City = familyPhone1City;
	}
	public String getFamilyPhone2City() {
		return familyPhone2City;
	}
	public void setFamilyPhone2City(String familyPhone2City) {
		this.familyPhone2City = familyPhone2City;
	}
	public String getWorkPhone1() {
		return workPhone1;
	}
	public void setWorkPhone1(String workPhone1) {
		this.workPhone1 = workPhone1;
	}
	public String getWorkPhone2() {
		return workPhone2;
	}
	public void setWorkPhone2(String workPhone2) {
		this.workPhone2 = workPhone2;
	}
	public String getWorkPhone1City() {
		return workPhone1City;
	}
	public void setWorkPhone1City(String workPhone1City) {
		this.workPhone1City = workPhone1City;
	}
	public String getWorkPhone2City() {
		return workPhone2City;
	}
	public void setWorkPhone2City(String workPhone2City) {
		this.workPhone2City = workPhone2City;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail1Note() {
		return email1Note;
	}
	public void setEmail1Note(String email1Note) {
		this.email1Note = email1Note;
	}
	public String getEmail2Note() {
		return email2Note;
	}
	public void setEmail2Note(String email2Note) {
		this.email2Note = email2Note;
	}
	public String getQq1() {
		return qq1;
	}
	public void setQq1(String qq1) {
		this.qq1 = qq1;
	}
	public String getQq2() {
		return qq2;
	}
	public void setQq2(String qq2) {
		this.qq2 = qq2;
	}
	public String getQq1Note() {
		return qq1Note;
	}
	public void setQq1Note(String qq1Note) {
		this.qq1Note = qq1Note;
	}
	public String getQq2Note() {
		return qq2Note;
	}
	public void setQq2Note(String qq2Note) {
		this.qq2Note = qq2Note;
	}
	public String getMsn1() {
		return msn1;
	}
	public void setMsn1(String msn1) {
		this.msn1 = msn1;
	}
	public String getMsn2() {
		return msn2;
	}
	public void setMsn2(String msn2) {
		this.msn2 = msn2;
	}
	public String getMsn1Note() {
		return msn1Note;
	}
	public void setMsn1Note(String msn1Note) {
		this.msn1Note = msn1Note;
	}
	public String getMsn2Note() {
		return msn2Note;
	}
	public void setMsn2Note(String msn2Note) {
		this.msn2Note = msn2Note;
	}
	public String getYahoo1() {
		return yahoo1;
	}
	public void setYahoo1(String yahoo1) {
		this.yahoo1 = yahoo1;
	}
	public String getYahoo2() {
		return yahoo2;
	}
	public void setYahoo2(String yahoo2) {
		this.yahoo2 = yahoo2;
	}
	public String getYahoo1Note() {
		return yahoo1Note;
	}
	public void setYahoo1Note(String yahoo1Note) {
		this.yahoo1Note = yahoo1Note;
	}
	public String getYahoo2Note() {
		return yahoo2Note;
	}
	public void setYahoo2Note(String yahoo2Note) {
		this.yahoo2Note = yahoo2Note;
	}
	public String getAliwang1() {
		return aliwang1;
	}
	public void setAliwang1(String aliwang1) {
		this.aliwang1 = aliwang1;
	}
	public String getAliwang2() {
		return aliwang2;
	}
	public void setAliwang2(String aliwang2) {
		this.aliwang2 = aliwang2;
	}
	public String getAliwang1Note() {
		return aliwang1Note;
	}
	public void setAliwang1Note(String aliwang1Note) {
		this.aliwang1Note = aliwang1Note;
	}
	public String getAliwang2Note() {
		return aliwang2Note;
	}
	public void setAliwang2Note(String aliwang2Note) {
		this.aliwang2Note = aliwang2Note;
	}
	public String getWeb1() {
		return web1;
	}
	public void setWeb1(String web1) {
		this.web1 = web1;
	}
	public String getWeb2() {
		return web2;
	}
	public void setWeb2(String web2) {
		this.web2 = web2;
	}
	public String getWeb1Note() {
		return web1Note;
	}
	public void setWeb1Note(String web1Note) {
		this.web1Note = web1Note;
	}
	public String getWeb2Note() {
		return web2Note;
	}
	public void setWeb2Note(String web2Note) {
		this.web2Note = web2Note;
	}
	public String getOther1() {
		return other1;
	}
	public void setOther1(String other1) {
		this.other1 = other1;
	}
	public String getOther2() {
		return other2;
	}
	public void setOther2(String other2) {
		this.other2 = other2;
	}
	public String getOther1Note() {
		return other1Note;
	}
	public void setOther1Note(String other1Note) {
		this.other1Note = other1Note;
	}
	public String getOther2Note() {
		return other2Note;
	}
	public void setOther2Note(String other2Note) {
		this.other2Note = other2Note;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public ContactType getContactType() {
		return contactType;
	}
	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

}
