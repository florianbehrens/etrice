#include "DataClass1.h"

#include <string.h>

//--------------------- begin user code
	// usercode3
//--------------------- end user code

// TODO: do we need setters and getters for C and C++ ?

//--------------------- operations
int32 DataClass1_MultiplyWithAttr1(DataClass1* self, int32 value) {
	return self->Attr1*value;
}
float32 DataClass1_MultiplyWithAttr3(DataClass1* self, float32 value) {
	return self->Attr3*value;
}

// deep copy
void DataClass1_deepCopy(DataClass1* source, DataClass1* target) {
	memcpy(target, source, sizeof(DataClass1));
}

