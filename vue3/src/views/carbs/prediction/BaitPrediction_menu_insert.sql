-- 注意：该页面对应的前台目录为views/prediction文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2023100810324090270', NULL, '投喂量预测', '/prediction/baitPredictionList', 'prediction/BaitPredictionList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2023-10-08 22:32:27', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023100810324090271', '2023100810324090270', '添加投喂量预测', NULL, NULL, 0, NULL, NULL, 2, 'prediction:bait_prediction:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-10-08 22:32:27', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023100810324090272', '2023100810324090270', '编辑投喂量预测', NULL, NULL, 0, NULL, NULL, 2, 'prediction:bait_prediction:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-10-08 22:32:27', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023100810324090273', '2023100810324090270', '删除投喂量预测', NULL, NULL, 0, NULL, NULL, 2, 'prediction:bait_prediction:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-10-08 22:32:27', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023100810324090274', '2023100810324090270', '批量删除投喂量预测', NULL, NULL, 0, NULL, NULL, 2, 'prediction:bait_prediction:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-10-08 22:32:27', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023100810324090275', '2023100810324090270', '导出excel_投喂量预测', NULL, NULL, 0, NULL, NULL, 2, 'prediction:bait_prediction:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-10-08 22:32:27', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023100810324090276', '2023100810324090270', '导入excel_投喂量预测', NULL, NULL, 0, NULL, NULL, 2, 'prediction:bait_prediction:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-10-08 22:32:27', NULL, NULL, 0, 0, '1', 0);