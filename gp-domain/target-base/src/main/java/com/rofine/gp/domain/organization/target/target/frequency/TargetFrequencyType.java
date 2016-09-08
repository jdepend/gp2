//Source file: E:\\my_workspace\\workprojects\\201605��Ʒ�з�\\project\\gp-domain\\src\\main\\java\\com\\rofine\\gp\\domain\\DesignModel\\DesignElement\\domain\\organization\\target\\target\\frequency\\TargetFrequencyType.java

package com.rofine.gp.domain.organization.target.target.frequency;

import java.util.List;

import com.rofine.gp.domain.organization.target.TargetException;
import com.rofine.gp.platform.exception.GpException;


public interface TargetFrequencyType 
{
   /**
    * @throws GpException 
 * @roseuid 573A6B6200C3
    */
   public List<TargetFrequency> createTargetFrequencys() throws TargetException;
}
