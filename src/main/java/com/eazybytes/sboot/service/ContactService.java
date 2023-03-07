package com.eazybytes.sboot.service;

import com.eazybytes.sboot.constants.sbootConstants;
import com.eazybytes.sboot.model.Contact;
import com.eazybytes.sboot.repository.contactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Slf4j
@Service
public class ContactService {
    @Autowired
    private contactRepository contactRepository;

    public ContactService(){
        System.out.println("Contact Service Bean initialized");
    }
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved=false;
        contact.setStatus(sbootConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if (savedContact.getContactId()>0){
            isSaved=true;
        }
        return isSaved;
    }

    public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatus(sbootConstants.OPEN, pageable);
        return msgPage;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        int rows = contactRepository.updateStatusById(sbootConstants.CLOSE,contactId);
        if(rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}
