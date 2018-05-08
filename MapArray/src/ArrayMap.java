import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

public class ArrayMap<K, V> implements Map<K, V>{
	private int size;
	private ArrayList<ArrayMapEntry<K, V>> theMap;

	public ArrayMap() {
		size = 0;
		theMap = new ArrayList<>();
	}


	private static class ArrayMapEntry<K, V> implements Entry<K, V>{
		private K key;
		private V value;

		public ArrayMapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}

		public String toString() {
			return "(" + key + ", " + value + ")";
		}

	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public V get(K key) {
		for(ArrayMapEntry<K, V> entry : theMap) {
			if (entry.key.equals(key)) {
				return entry.value;
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		//Replace Value by Looking for entry
		for(ArrayMapEntry<K, V> entry : theMap) {					//O(N)
			if (entry.key.equals(key)) {	
				V oldValue = entry.value;
				entry.value = value;		//Replacing Value
				return oldValue;
			}
		}

		//Add the value if it doesn't exist
		theMap.add(new ArrayMapEntry<K, V>(key, value)); 			//O(N)
		size++;
		return null;
	}

	@Override
	public V remove(K key) {
		Iterator<ArrayMapEntry<K, V>> it = theMap.iterator();		//Invoking an ArrayList's iterator

		while(it.hasNext()) {									
			ArrayMapEntry<K, V> entry = it.next();
			if (entry.key.equals(key)) {
				V oldValue = entry.value;
				it.remove();
				size--;
				return oldValue;
			}
		}
		return null;
	}

	@Override
	public Iterable<K> keySet() {
		ArrayList<K> keys = new ArrayList<>(theMap.size());	//Return an arraylist which is iterable!!!!

		for(ArrayMapEntry<K, V> entry : theMap) {
			keys.add(entry.key);
		}
		return keys;
	}

	@Override
	public Iterable<V> valueSet() {
		ArrayList<V> values = new ArrayList<>(theMap.size());	//Return an arraylist which is iterable!!!!

		for(ArrayMapEntry<K, V> entry : theMap) {
			values.add(entry.value);
		}
		return values;
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K, V>> entries = new ArrayList<>();
		
		for(ArrayMapEntry<K, V> entry: theMap) {
			entries.add(entry);
		}
		return entries;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(ArrayMapEntry entry: theMap) {
			sb.append(entry.toString() + " ");
		}
		return sb.toString();
	}

}
