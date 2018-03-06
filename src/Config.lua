function Initialize()
  Table = {}

end

io.input(data.txt)

local lines = {}
for line in io.lines() do
  local dateEx = "%d%d/%d%d/%d%d%d%d"
  st, en = string.find(line, date)
  local name = string.sub(line, 1, st - 1)
  local date = string.sub(line, st)
  local i = Table:new(name)
  table.insert(lines, line)
end


function Table:new(name, date)
 setmetatable({}, self)
 Table.__index = self
 self.name = name
 self.date = date
 return self
end

function Table:getName(self)
  return self.name
end

function Table:getDate(self)
 return self.date
end

function Table:getDateValue(self)
  local date = self.date
  return date[3] * 10000 + date[1] * 100 + date[2]
end


function split(str, pat)
   local t = {}  -- note: use {n = 0} in Lua-5.0
   local fpat = "(.-)" .. pat
   local last_end = 1
   local s, e, cap = str:find(fpat, 1)
   while s do
      if s ~= 1 or cap ~= "" then
	 table.insert(t,cap)
      end
      last_end = e+1
      s, e, cap = str:find(fpat, last_end)
   end
   if last_end <= #str then
      cap = str:sub(last_end)
      table.insert(t, cap)
   end
   return t
end
