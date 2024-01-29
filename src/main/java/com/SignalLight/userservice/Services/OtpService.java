package com.SignalLight.userservice.Services;

import java.util.Date; 
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SignalLight.userservice.Repositories.OtpDao;
import com.SignalLight.userservice.entity.OtpMaster;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;


@Component
public class OtpService   {
	
	@Autowired
	private OtpDao otpDao;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	
	
	public int generateOTP(){
		Random random = new Random();
		int otp = 100000 + random.nextInt(900000);
		return otp;
	} 
	
		
	public void generateUserOtp (String mmobilenumber) {
		
		OtpMaster otp = new OtpMaster();
		otp.setMobilenumber(mmobilenumber);
		otp.setOtp(generateOTP());
		otp.setCreateDateTime(new Date());
		otp.setStatus(true);
		otpDao.save(otp);
	}

	public Optional<OtpMaster> getOtpID(int id) {
		return otpDao.findById(id); 
	}
	
	public int validateMobilenumber(String p_mobilenumber) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("validate_mobilenumber");
        query.registerStoredProcedureParameter("p_mobilenumber", String.class, ParameterMode.IN);
        query.setParameter("p_mobilenumber", p_mobilenumber);
        query.registerStoredProcedureParameter("p_outval", Integer.class, ParameterMode.OUT);
        query.execute();
        Integer p_outval = (Integer) query.getOutputParameterValue("p_outval");
        return p_outval;
    }
	
	public int validatePremiseMobilenumber(String p_mobilenumber) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("CheckMobileNumberExists");
        query.registerStoredProcedureParameter("mobile_number", String.class, ParameterMode.IN);
        query.setParameter("mobile_number", p_mobilenumber);
        query.registerStoredProcedureParameter("result", Integer.class, ParameterMode.OUT);
        query.execute();
        Integer p_outval = (Integer) query.getOutputParameterValue("result");
        return p_outval;
    }
	
    public int validateOtp(int p_Otp, String p_mobilenumber) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("otpValidate");        
        query.registerStoredProcedureParameter("p_Otp", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_mobilenumber", String.class, ParameterMode.IN);
        query.setParameter("p_Otp", p_Otp);
        query.setParameter("p_mobilenumber", p_mobilenumber);
        query.registerStoredProcedureParameter("p_outval", Integer.class, ParameterMode.OUT);
        query.execute();
        Integer p_outval = (Integer) query.getOutputParameterValue("p_outval");
        return p_outval;
    }
	
}
