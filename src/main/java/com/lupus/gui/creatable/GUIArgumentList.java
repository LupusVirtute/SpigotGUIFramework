package com.lupus.gui.creatable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unchecked")
public class GUIArgumentList {
	public GUIArgumentList(Object... arguments){
		argumentList.addAll(Arrays.asList(arguments));
	}
	private static final IllegalArgumentException illegalIDX = new IllegalArgumentException("Illegal Index value");
	private static final ClassCastException invalidGenericType = new ClassCastException("Failed to get generic type exact");
	private static final Exception noTypesFound = new Exception("No eligble types found");
	List<Object> argumentList = new ArrayList<>();
	public <E> E getArg(int idx,Class<E> type) throws IllegalArgumentException,ClassCastException {
		if (argumentList.size() < idx || idx < 0){
			throw illegalIDX;
		}

		Object obj = argumentList.get(idx);
		if (type.isInstance(obj)){
			return (E)obj;
		}
		throw invalidGenericType;
	}
	public <E> E getFirstAnyArgType(Class<E> type) throws Exception{
		for (Object o : argumentList) {
			if (type.isInstance(o))
				return (E)o;
		}
		throw noTypesFound;
	}
	public <E> boolean isThereInstanceOf(Class<E> type){
		for (Object o : argumentList)
			if (type.isInstance(o))
				return true;
		return false;
	}
	public <E> List<E> getArrayArgType(Class<E> type){
		List<E> array = new ArrayList<>();
		for (Object o : argumentList) {
			if (type.isInstance(o))
				array.add((E)o);
		}
		return array;
	}
}
