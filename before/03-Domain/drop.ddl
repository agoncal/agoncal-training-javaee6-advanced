alter table Item drop constraint FK_3l936squa2jyegmpfdmwdjxve if exists
alter table Item drop constraint FK_59pwuale7q0ni8w5wtq0i0sp9 if exists
alter table Item drop constraint FK_69mtted1ntbtu6uem7r2oeqcr if exists
alter table Item drop constraint FK_5grk9vh95m6dn4hxfcqep37rf if exists
alter table Item drop constraint FK_9nees2bb8k5ekvfkw191v5b53 if exists
alter table Item_Musician drop constraint FK_hehk2l0jdnr6mmfow8e2cihjh if exists
alter table Item_Musician drop constraint FK_6c7n156icts4eurpb228ovemy if exists
alter table OrderLine drop constraint FK_5m5mt76jhwi3oay9io04agi6p if exists
alter table PurchaseOrder drop constraint FK_931tsmy9p34wiglc3k8c40t91 if exists
alter table PurchaseOrder_OrderLine drop constraint FK_2il8k1fa4ocwhknp0nilrbusb if exists
alter table PurchaseOrder_OrderLine drop constraint FK_fflqvkxksae9h98plotgdsia if exists

drop table Author if exists
drop table Category if exists
drop table Customer if exists
drop table Genre if exists
drop table Item if exists
drop table Item_Musician if exists
drop table MajorLabel if exists
drop table Musician if exists
drop table OrderLine if exists
drop table Publisher if exists
drop table PurchaseOrder if exists
drop table PurchaseOrder_OrderLine if exists
