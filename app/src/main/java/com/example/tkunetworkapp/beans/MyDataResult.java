package com.example.tkunetworkapp.beans;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Xavier on 2015/10/30.
 */
public class MyDataResult {
	private Result result;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	// Inner Classes
	public static class Result {

           /////////////////////////////////////////////////////////////

		private String offset;

		@SerializedName("limit")
		private String limitation;

		private String count;

		private String sort;

		private List<ResultItem> results;





		public String getOffset() {
			return offset;
		}

		public void setOffset(String offset) {
			this.offset = offset;
		}

		public String getLimitation() {
			return limitation;
		}

		public void setLimitation(String limitation) {
			this.limitation = limitation;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}

		public List<ResultItem> getResults() {
			return results;
		}

		public void setResults(List<ResultItem> results) {
			this.results = results;
		}
	}

	public static class ResultItem {
		private String _id;
		private String AC_NO;
		private String ST_NO;
		private String SNO;
		private String APPMODE;
		private String X;
		private String Y;
		private String APPTIME;
		private String APP_NAME;
		private String C_NAME;
		private String ADDR;
		private String CB_DA;
		private String CE_DA;
		private String CO_TI;
		private String TC_MA;
		private String TC_TL;
		private String TC_MA3;
		private String TC_TL3;
		private String NPURP;
		private String DTYPE;

		public String get_id() {
			return _id;
		}
		public void set_id(String _id) {
			this._id = _id;
		}

		public String getCO_TI() {
			return CO_TI;
		}
		public void setCO_TI(String CO_TI) {
			this.CO_TI = CO_TI;
		}

		public String getST_NO() {
			return ST_NO;
		}
		public void setST_NO(String ST_NO) {
			this.ST_NO = ST_NO;
		}

		public String getSNO() {
			return SNO;
		}
		public void setSNO(String SNO) {
			this.SNO = SNO;
		}

		public String getAPPMODE() {
			return APPMODE;
		}
		public void setAPPMODE(String APPMODE) {
			this.APPMODE = APPMODE;
		}

		public String getC_NAME() {
			return  C_NAME;
		}
		public void setC_NAME(String C_NAME) {
			this.C_NAME = C_NAME;
		}

		public String getADDR() {
			return ADDR;
		}
		public void setADDR(String ADDR) {
			this.ADDR = ADDR;
		}

		public String getAPP_NAME() {
			return APP_NAME;
		}
		public void setAPP_NAME(String APP_NAME) {
			this.APP_NAME = APP_NAME;
		}
	}
}
