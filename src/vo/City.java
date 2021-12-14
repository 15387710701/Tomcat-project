package vo;

public class City {
	private String cityCode;
	private String cityName;
	private String provinceCode;
	public City() {
		super();
	}
	public String getCityCode() {
		return cityCode;
	}
	public City(String cityCode, String cityName, String provinceCode) {
		super();
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.provinceCode = provinceCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
}
