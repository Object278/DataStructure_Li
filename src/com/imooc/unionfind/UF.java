package com.imooc.unionfind;

public interface UF {
	boolean isConnected(int p, int q);
	void unionElements(int p, int q);
	int getSize();
}
