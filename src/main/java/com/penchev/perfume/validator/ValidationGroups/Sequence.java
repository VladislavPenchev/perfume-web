package com.penchev.perfume.validator.ValidationGroups;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, LengthGroup.class, ValidGroup.class, ExistGroup.class})
public interface Sequence {}
