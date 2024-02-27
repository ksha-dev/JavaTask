package task;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;

import exceptions.CustomException;
import utility.Utility;

//Coding to the interface - Java

public class ListTask {
	
	public static Class<?> arrayList = null;
	
	static {
		try {
			arrayList = Class.forName("java.util.ArrayList");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@SuppressWarnings("unchecked")
	public <E> List<E> createList() throws Exception {
		return (List<E>) arrayList.getDeclaredConstructor().newInstance();
	}

	@SuppressWarnings("unchecked")
	public <E> List<E> createList(Collection<E> collection) throws Exception{
		Utility.validateCollection(collection);
		return (List<E>) arrayList.getConstructor(Collection.class).newInstance(collection);
	}

	public <E> int getSizeOfList(List<E> list) throws CustomException {
		Utility.validateObject(list);
		return list.size();
	}

	public <E> void addElementToList(List<E> list, E element, int index) throws CustomException {
		Utility.validateRange(index, getSizeOfList(list));
		Utility.validateObject(element);
		list.add(index, element);
	}

	public <E> void addElementToList(List<E> list, E element) throws CustomException {
		list.add(getSizeOfList(list), element);
	}

	public <E> void addCollectionToList(List<E> list, Collection<E> collection, int index)
			throws CustomException {
		Utility.validateRange(index, getSizeOfList(list), "Invalid Range");
		Utility.validateCollection(collection);
		list.addAll(index, collection);
	}

	public <E> void addCollectionToList(List<E> list, Collection<E> collection) throws CustomException {
		Utility.validateCollection(collection);
		addCollectionToList(list, collection, getSizeOfList(list));
	}

	public <E> void addElementsToList(List<E> list, E[] elements) throws CustomException {
		Utility.validateObject(elements);
		for (E element : elements) {
			addElementToList(list, element);
		}
	}

	public <E> int getIndexOfElement(List<E> list, E element) throws CustomException {
		Utility.validateObject(list);
		Utility.validateObject(element);
		return list.indexOf(element);
	}

	public <E> int getLastIndexOfElement(List<E> list, E element) throws CustomException {
		Utility.validateObject(list);
		Utility.validateObject(element);
		return list.lastIndexOf(element);
	}

	public <E> Iterator<E> getIterator(List<E> list) throws CustomException {
		Utility.validateObject(list);
		return  list.iterator();
	}

	public <E> E getElementAtIndex(List<E> list, int index) throws CustomException {
		Utility.validateRange(index, getSizeOfList(list), "Invalid index.");
		return list.get(index);
	}

	public <E> List<E> getSubList(List<E> list, int start, int end) throws Exception {
		Utility.validateRange(end, getSizeOfList(list), "Invalid range");
		Utility.validateRange(start, end);
		return createList(list.subList(start, end));
	}

	public <E> void removeCollection(List<E> list, Collection<E> collection) throws CustomException {
		Utility.validateObject(list);
		Utility.validateCollection(collection);
		list.removeAll(collection);
	}

	public <E> void removeElementAtIndex(List<E> list, int index) throws CustomException {
		Utility.validateRange(index, getSizeOfList(list));
		list.remove(index);
	}
	
	public <E> void retainCollect(List<E> list, Collection<E> collection) throws CustomException {
		Utility.validateObject(list);
		Utility.validateCollection(collection);
		list.retainAll(collection);
	}
	
	public <E> void clearList(List<E> list) throws CustomException {
		Utility.validateObject(list);
		list.clear();
	}
	
	public <E> boolean hasObject(List<E> list, E object) throws CustomException {
		Utility.validateObject(list);
		Utility.validateObject(object);
		return list.contains(object);
	}
}