package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Subgreennit;

import java.util.Comparator;

public class SubgreennitComparator implements Comparator<Subgreennit> {
    @Override
    public int compare(Subgreennit o1, Subgreennit o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
