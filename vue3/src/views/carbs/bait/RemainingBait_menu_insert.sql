-- 注意：该页面对应的前台目录为views/bait文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2023090304112700240', NULL, '残饵计数识别', '/bait/remainingBaitList', 'bait/RemainingBaitList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2023-09-03 16:11:24', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023090304112700241', '2023090304112700240', '添加残饵计数识别', NULL, NULL, 0, NULL, NULL, 2, 'bait:remaining_bait:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-03 16:11:24', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023090304112700242', '2023090304112700240', '编辑残饵计数识别', NULL, NULL, 0, NULL, NULL, 2, 'bait:remaining_bait:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-03 16:11:24', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023090304112700243', '2023090304112700240', '删除残饵计数识别', NULL, NULL, 0, NULL, NULL, 2, 'bait:remaining_bait:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-03 16:11:24', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023090304112700244', '2023090304112700240', '批量删除残饵计数识别', NULL, NULL, 0, NULL, NULL, 2, 'bait:remaining_bait:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-03 16:11:24', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023090304112700245', '2023090304112700240', '导出excel_残饵计数识别', NULL, NULL, 0, NULL, NULL, 2, 'bait:remaining_bait:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-03 16:11:24', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2023090304112700246', '2023090304112700240', '导入excel_残饵计数识别', NULL, NULL, 0, NULL, NULL, 2, 'bait:remaining_bait:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2023-09-03 16:11:24', NULL, NULL, 0, 0, '1', 0);