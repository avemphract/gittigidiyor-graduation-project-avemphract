package dev.patika.customerserver.utils;

import dev.patika.customerserver.entities.Message;
import dev.patika.customerserver.entities.enums.MessageType;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory {

    public Message createCreditApprovalMessage(boolean isApproval, long phoneNumber, double givenCreditAmount){
        if (isApproval)
            return new Message(MessageType.CREDIT_APPROVAL_RESULT,phoneNumber,"Your credit application has been approved. Approved credit amount: "+givenCreditAmount);
        else
            return new Message(MessageType.CREDIT_APPROVAL_RESULT,phoneNumber,"Your credit application has not been approved");
    }


}
