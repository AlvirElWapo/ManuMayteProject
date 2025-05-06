package logic;

public class HashMap<K, V> {
  private final int INITIAL_CAPACITY = 16;
  private Entry<K, V>[] buckets;

  public HashMap() {
    buckets = (Entry<K, V>[]) new Entry[INITIAL_CAPACITY];
  }

  private int getBucketIndex(K key) {
    return Math.abs(key.hashCode() % INITIAL_CAPACITY);
  }

  public void put(K key, V value) {
    int index = getBucketIndex(key);
    Entry<K, V> existing = buckets[index];

    if (existing == null) {
      buckets[index] = new Entry<>(key, value);
      return;
    }

    while (existing != null) {
      if (existing.key.equals(key)) {
        existing.value = value; 
        return;
      }
      if (existing.next == null) break;
      existing = existing.next;
    }

    existing.next = new Entry<>(key, value); 
  }

  public V get(K key) {
    int index = getBucketIndex(key);
    Entry<K, V> current = buckets[index];

    while (current != null) {
      if (current.key.equals(key)) return current.value;
      current = current.next;
    }

    return null;
  }

  public boolean remove(K key) {
    int index = getBucketIndex(key);
    Entry<K, V> current = buckets[index];
    Entry<K, V> prev = null;

    while (current != null) {
      if (current.key.equals(key)) {
        if (prev == null)
          buckets[index] = current.next;
        else
          prev.next = current.next;

        return true;
      }
      prev = current;
      current = current.next;
    }

    return false;
  }

  public void printMap() {
    System.out.println("Map contents:");
    for (int i = 0; i < buckets.length; i++) {
      Entry<K, V> current = buckets[i];
      if (current != null) {
        System.out.print("Bucket " + i + ": ");
        while (current != null) {
          System.out.print(current.key + "=" + current.value + " ");
          current = current.next;
        }
        System.out.println();
      }
    }
  }
}
