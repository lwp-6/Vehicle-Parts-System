package com.example.springboottest1.service;

import com.example.springboottest1.entity.Parts;
import com.example.springboottest1.mapper.partsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartsService {
    @Autowired
    private partsMapper partsmapper;
    public List<Parts> partsSearchbyname(String partsname)
    {
        return partsmapper.partsSearchbyname(partsname);
    }
    public List<Parts> partsSearchbyusername(String username)
    {
        return partsmapper.partsSearchbyusername(username);
    }

    public int addparts(String partsname, int quantity, double price, String username, String information)
    {
        return partsmapper.addparts(partsname, quantity, price, username, information);
    }
    public int deleteparts(int partsid)

    {
        return partsmapper.deleteparts(partsid);
    }
    public int updateparts(int id, String partsname, int quantity, double price, String information)
    {
        return partsmapper.updateparts(id, partsname, quantity, price, information);
    }
    public Parts partsSearchbyid(int id)
    {
        return partsmapper.partsSearchbyid(id);
    }
    public List<Parts>partsSearchall()
    {
        return partsmapper.partsSearchall();
    }
}
