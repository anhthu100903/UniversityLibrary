/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;
import java.util.List;
/**
 *
 * @author anhthu
 */
public interface DAO_Interface<T> {
    public int add(T t);
    public int update(T t);
    public int delete (String id);
    public List<T> selectAll ();
    public T selectById (String id);
}
